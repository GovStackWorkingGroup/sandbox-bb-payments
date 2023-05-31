package global.govstack.payment.bb.mock.service;

import global.govstack.payment.bb.mock.dto.BeneficiaryDTO;
import global.govstack.payment.bb.mock.model.Beneficiary;
import global.govstack.payment.bb.mock.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepository repository;

    public List<Beneficiary> convertToEntity(List<BeneficiaryDTO> beneficiaries) {
        return beneficiaries
                .stream()
                .map(BeneficiaryDTO::transformToEntity)
                .collect(Collectors.toList());
    }

    public Boolean beneficiariesExists(List<Beneficiary> beneficiaries){
        return beneficiaries
                .stream()
                .allMatch(n -> repository.existsBeneficiaryByPayeeFunctionalID(n.getPayeeFunctionalID()) == true);
    }

    public List<Beneficiary> saveBeneficiaries(List<Beneficiary> beneficiaries) throws RuntimeException {
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

}
