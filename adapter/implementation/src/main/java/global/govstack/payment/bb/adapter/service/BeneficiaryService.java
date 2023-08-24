package global.govstack.payment.bb.adapter.service;

import global.govstack.payment.bb.adapter.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.payment.bb.adapter.configuration.PaymentProperties;
import global.govstack.payment.bb.adapter.dto.*;
import global.govstack.payment.bb.adapter.model.Beneficiary;
import global.govstack.payment.bb.adapter.repository.BeneficiaryRepository;
import global.govstack.payment.bb.adapter.service.exception.BeneficiaryServiceException;
import global.govstack.payment.bb.adapter.service.exception.CreditInstructionServiceException;
import global.govstack.payment.bb.adapter.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {
    @Autowired
    BeneficiaryRepository repository;
    private final HttpHeaders httpHeaders;
    private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties;
    private final PaymentProperties paymentProperties;

    public BeneficiaryService(PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties, PaymentProperties paymentProperties) {
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

    private Beneficiary transformToEntity(RegisterbeneficiaryBeneficiaries beneficiary) {
        return new Beneficiary(beneficiary.getPayeeFunctionalID(), beneficiary.getPaymentModality(), beneficiary.getFinancialAddress());
    }

    private List<Beneficiary> convertToEntities(List<RegisterbeneficiaryBeneficiaries> beneficiaries) {
        return beneficiaries
                .stream()
                .map(n -> transformToEntity(n))
                .collect(Collectors.toList());
    }

    private Boolean beneficiariesExists(List<Beneficiary> beneficiaries){
        return beneficiaries
                .stream()
                .allMatch(n -> repository.existsBeneficiaryByPayeeFunctionalID(n.getPayeeFunctionalID()));
    }

    private void saveBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
        if(beneficiariesExists(beneficiaries)){
            throw new BeneficiaryServiceException("Beneficiary exist!");
        }
        repository.saveAll(beneficiaries);
    }

    private void updateBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
        if(!beneficiariesExists(beneficiaries)){
            throw new BeneficiaryServiceException("Beneficiary does not exist!");
        }
        repository.saveAll(beneficiaries);
    }

    @Transactional
    public PaymentResponseDTO register(RegisterbeneficiaryBody body) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.registerBeneficiary(),
                    new HttpEntity<>(body, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,ex.getMessage());
        }
    }

    @Transactional
    public PaymentResponseDTO update(UpdatebeneficiarydetailsBody body) {
        try {
        return new RestTemplate().postForObject(
                paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.updateBeneficiary(),
                new HttpEntity<>(body, httpHeaders),
                PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,ex.getMessage());
        }
    }
}
