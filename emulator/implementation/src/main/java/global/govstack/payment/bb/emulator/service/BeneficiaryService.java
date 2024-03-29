package global.govstack.payment.bb.emulator.service;

import global.govstack.payment.bb.emulator.dto.InlineResponse200;
import global.govstack.payment.bb.emulator.dto.RegisterbeneficiaryBeneficiaries;
import global.govstack.payment.bb.emulator.dto.RegisterbeneficiaryBody;
import global.govstack.payment.bb.emulator.dto.UpdatebeneficiarydetailsBody;
import global.govstack.payment.bb.emulator.model.Beneficiary;
import global.govstack.payment.bb.emulator.repository.BeneficiaryRepository;
import global.govstack.payment.bb.emulator.service.exception.BeneficiaryServiceException;
import global.govstack.payment.bb.emulator.service.exception.ServiceException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficiaryService {
  @Autowired BeneficiaryRepository repository;

  private Beneficiary transformToEntity(RegisterbeneficiaryBeneficiaries beneficiary) {
    return new Beneficiary(
        beneficiary.getPayeeFunctionalID(),
        beneficiary.getPaymentModality(),
        beneficiary.getFinancialAddress());
  }

  private List<Beneficiary> convertToEntities(
      List<RegisterbeneficiaryBeneficiaries> beneficiaries) {
    return beneficiaries.stream().map(n -> transformToEntity(n)).collect(Collectors.toList());
  }

  private Boolean beneficiariesExists(List<Beneficiary> beneficiaries) {
    return beneficiaries.stream()
        .allMatch(n -> repository.existsBeneficiaryByPayeeFunctionalID(n.getPayeeFunctionalID()));
  }

  private void saveBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
    if (beneficiariesExists(beneficiaries)) {
      throw new BeneficiaryServiceException("Beneficiary exist!");
    }
    repository.saveAll(beneficiaries);
  }

  private void updateBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
    if (!beneficiariesExists(beneficiaries)) {
      throw new BeneficiaryServiceException("Beneficiary does not exist!");
    }
    repository.saveAll(beneficiaries);
  }

  @Transactional
  public InlineResponse200 register(RegisterbeneficiaryBody body) {
    try {
      List<Beneficiary> beneficiaries = convertToEntities(body.getBeneficiaries());
      saveBeneficiaries(beneficiaries);
    } catch (BeneficiaryServiceException e) {
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
        .responseDescription("Beneficiaries registered!");
  }

  @Transactional
  public InlineResponse200 update(UpdatebeneficiarydetailsBody body) {
    try {
      List<Beneficiary> beneficiaries = convertToEntities(body.getBeneficiaries());
      updateBeneficiaries(beneficiaries);
    } catch (BeneficiaryServiceException e) {
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
        .responseDescription("Beneficiaries updated!");
  }
}
