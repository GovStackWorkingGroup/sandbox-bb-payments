package global.govstack.payment.bb.adapter.service;

import global.govstack.payment.bb.adapter.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.payment.bb.adapter.configuration.PaymentProperties;
import global.govstack.payment.bb.adapter.dto.*;
import global.govstack.payment.bb.adapter.model.CreditInstruction;
import global.govstack.payment.bb.adapter.repository.BeneficiaryRepository;
import global.govstack.payment.bb.adapter.repository.CreditInstructionRepository;
import global.govstack.payment.bb.adapter.service.exception.CreditInstructionServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditInstructionService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private CreditInstructionRepository creditInstructionRepository;

    private final HttpHeaders httpHeaders;
    private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties;
    private final PaymentProperties paymentProperties;

    public CreditInstructionService(PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties, PaymentProperties paymentProperties) {
        this.paymentBBInformationMediatorProperties = paymentBBInformationMediatorProperties;
        this.paymentProperties = paymentProperties;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Road-Client", paymentBBInformationMediatorProperties.header());
    }

    public String health() {
        return new RestTemplate().exchange(
                paymentBBInformationMediatorProperties.baseUrl() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
    }

    private CreditInstruction transformPrepaymentvalidationCreditInstructionsToEntity(PrepaymentvalidationCreditInstructions instruction) {
        return new CreditInstruction.CreditInstructionBuilder()
                .setInstructionID(instruction.getInstructionID())
                .setAmount(instruction.getAmount())
                .setCurrency(instruction.getCurrency())
                .setNarration(instruction.getNarration())
                .setPayeeFunctionalID(instruction.getPayeeFunctionalID())
                .setValid(true)
                .build();
    }

    private List<CreditInstruction> covertPrepaymentvalidationCreditInstructionsToEntity(List<PrepaymentvalidationCreditInstructions> creditInstructions) {
        return creditInstructions
                .stream()
                .map(n -> transformPrepaymentvalidationCreditInstructionsToEntity(n))
                .collect(Collectors.toList());
    }

    private void validatePrepaymentCreditInstructions(List<PrepaymentvalidationCreditInstructions> creditInstructions) {
        List<String> ids = creditInstructions
                .stream()
                .map(PrepaymentvalidationCreditInstructions::getPayeeFunctionalID)
                .collect(Collectors.toList());

        if (!beneficiariesExistsByPayeeFunctionalID(ids)) {
            throw new CreditInstructionServiceException("Beneficiary doesn't exist!");
        }
    }

    private List<CreditInstruction> getValidBulkpaymentCreditInstructions(List<BulkpaymentCreditInstructions> creditInstructions) {
        return creditInstructions
                .stream()
                .map(n -> creditInstructionRepository
                                .findValidatedCreditInstruction(n.getAmount(), n.getCurrency(), n.getInstructionID(), n.getNarration(), n.getPayeeFunctionalID())
                                .orElseThrow(() -> new CreditInstructionServiceException("No valid credit instruction for Instruction Id: \"" + n.getInstructionID() + "\"!"))
                )
                .collect(Collectors.toList());
    }

    private Boolean beneficiariesExistsByPayeeFunctionalID(List<String> ids){
        return ids
                .stream()
                .allMatch(beneficiaryRepository::existsBeneficiaryByPayeeFunctionalID);
    }

    @Transactional
    public PaymentResponseDTO prepaymentValidation(PrepaymentvalidationBody body) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.prepaymentValidation(),
                    new HttpEntity<>(body, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    @Transactional
    public PaymentResponseDTO bulkPayment(BulkpaymentBody body) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.bulkPayment(),
                    new HttpEntity<>(body, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }
}
