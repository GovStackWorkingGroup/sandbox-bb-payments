package global.govstack.payment.bb.mock.service;

import global.govstack.payment.bb.mock.dto.InlineResponse200;
import global.govstack.payment.bb.mock.dto.RegisterbeneficiaryBeneficiaries;
import global.govstack.payment.bb.mock.dto.RegisterbeneficiaryBody;
import global.govstack.payment.bb.mock.dto.UpdatebeneficiarydetailsBody;
import global.govstack.payment.bb.mock.model.Beneficiary;
import global.govstack.payment.bb.mock.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {
    @Autowired
    BeneficiaryRepository repository;

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
    private List<Beneficiary> saveBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
        if(beneficiariesExists(beneficiaries)){
            throw new RuntimeException("Beneficiary exist!");
        }
        return repository.saveAll(beneficiaries);
    }

    public List<Beneficiary> updateBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
        if(!beneficiariesExists(beneficiaries)){
            throw new RuntimeException("Beneficiary does not exist!");
        }
        return repository.saveAll(beneficiaries);
    }

    @Transactional
    public InlineResponse200 register(RegisterbeneficiaryBody body) {
        try {
            List<Beneficiary> beneficiaries = convertToEntities(body.getBeneficiaries());
            saveBeneficiaries(beneficiaries);
        } catch (Exception e) {
            return new InlineResponse200()
                    .requestID(body.getRequestID())
                    .responseCode("01")
                    .responseDescription(e.getMessage());
        }
        return new InlineResponse200()
                .requestID(body.getRequestID())
                .responseCode("00")
                .responseDescription("OK");
    }

    @Transactional
    public InlineResponse200 update(UpdatebeneficiarydetailsBody body) {
        try {
            List<Beneficiary> beneficiaries = convertToEntities(body.getBeneficiaries());
            updateBeneficiaries(beneficiaries);
        } catch (Exception e) {
            return new InlineResponse200()
                    .requestID(body.getRequestID())
                    .responseCode("01")
                    .responseDescription(e.getMessage());
        }
        return new InlineResponse200()
                .requestID(body.getRequestID())
                .responseCode("00")
                .responseDescription("OK");
    }
}
