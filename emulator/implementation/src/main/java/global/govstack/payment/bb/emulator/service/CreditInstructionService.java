package global.govstack.payment.bb.emulator.service;

import global.govstack.payment.bb.emulator.dto.*;
import global.govstack.payment.bb.emulator.model.CreditInstruction;
import global.govstack.payment.bb.emulator.repository.BeneficiaryRepository;
import global.govstack.payment.bb.emulator.repository.CreditInstructionRepository;
import global.govstack.payment.bb.emulator.service.exception.CreditInstructionServiceException;
import global.govstack.payment.bb.emulator.service.exception.ServiceException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditInstructionService {

  @Autowired private BeneficiaryRepository beneficiaryRepository;
  @Autowired private CreditInstructionRepository creditInstructionRepository;

  private CreditInstruction transformPrepaymentvalidationCreditInstructionsToEntity(
      PrepaymentvalidationCreditInstructions instruction) {
    return new CreditInstruction.CreditInstructionBuilder()
        .setInstructionID(instruction.getInstructionID())
        .setAmount(instruction.getAmount())
        .setCurrency(instruction.getCurrency())
        .setNarration(instruction.getNarration())
        .setPayeeFunctionalID(instruction.getPayeeFunctionalID())
        .setValid(true)
        .build();
  }

  private List<CreditInstruction> covertPrepaymentvalidationCreditInstructionsToEntity(
      List<PrepaymentvalidationCreditInstructions> creditInstructions) {
    return creditInstructions.stream()
        .map(n -> transformPrepaymentvalidationCreditInstructionsToEntity(n))
        .collect(Collectors.toList());
  }

  private void validatePrepaymentCreditInstructions(
      List<PrepaymentvalidationCreditInstructions> creditInstructions) {
    List<String> ids =
        creditInstructions.stream()
            .map(PrepaymentvalidationCreditInstructions::getPayeeFunctionalID)
            .collect(Collectors.toList());

    if (!beneficiariesExistsByPayeeFunctionalID(ids)) {
      throw new CreditInstructionServiceException("Beneficiary doesn't exist!");
    }
  }

  private List<CreditInstruction> getValidBulkpaymentCreditInstructions(
      List<BulkpaymentCreditInstructions> creditInstructions) {
    return creditInstructions.stream()
        .map(
            n ->
                creditInstructionRepository
                    .findValidatedCreditInstruction(
                        n.getAmount(),
                        n.getCurrency(),
                        n.getInstructionID(),
                        n.getNarration(),
                        n.getPayeeFunctionalID())
                    .orElseThrow(
                        () ->
                            new CreditInstructionServiceException(
                                "No valid credit instruction for Instruction Id: \""
                                    + n.getInstructionID()
                                    + "\"!")))
        .collect(Collectors.toList());
  }

  private Boolean beneficiariesExistsByPayeeFunctionalID(List<String> ids) {
    return ids.stream().allMatch(beneficiaryRepository::existsBeneficiaryByPayeeFunctionalID);
  }

  @Transactional
  public InlineResponse200 prepaymentValidation(PrepaymentvalidationBody body) {
    try {
      validatePrepaymentCreditInstructions(body.getCreditInstructions());
      creditInstructionRepository.saveAll(
          covertPrepaymentvalidationCreditInstructionsToEntity(body.getCreditInstructions()));
    } catch (CreditInstructionServiceException e) {
      InlineResponse200 errorResponse =
          new InlineResponse200()
              .requestID(body.getRequestID())
              .responseCode("01")
              .responseDescription(e.getMessage());
      throw new ServiceException(e.getMessage(), errorResponse);
    }
    return new InlineResponse200()
        .requestID(body.getRequestID())
        .responseCode("00")
        .responseDescription("Payments Validated!");
  }

  @Transactional
  public InlineResponse200 bulkPayment(BulkpaymentBody body) {
    try {
      List<CreditInstruction> ci =
          getValidBulkpaymentCreditInstructions(body.getCreditInstructions());
      ci.forEach(n -> n.setOrdered(true));

      creditInstructionRepository.saveAll(ci);
    } catch (CreditInstructionServiceException e) {
      InlineResponse200 errorResponse =
          new InlineResponse200()
              .requestID(body.getRequestID())
              .responseCode("01")
              .responseDescription(e.getMessage());
      throw new ServiceException(e.getMessage(), errorResponse);
    }
    return new InlineResponse200()
        .requestID(body.getRequestID())
        .responseCode("00")
        .responseDescription("Payments ordered!");
  }
}
