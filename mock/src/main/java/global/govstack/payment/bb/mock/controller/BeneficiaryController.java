package global.govstack.payment.bb.mock.controller;

import global.govstack.payment.bb.mock.dto.BeneficiaryRequestDTO;
import global.govstack.payment.bb.mock.dto.BeneficiaryResponseDTO;
import global.govstack.payment.bb.mock.model.Beneficiary;
import global.govstack.payment.bb.mock.service.BeneficiaryV1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BeneficiaryController {

    @Autowired
    BeneficiaryV1Service beneficiaryService;

    @PostMapping(path = "register-beneficiary",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BeneficiaryResponseDTO registerBeneficiary(@RequestBody BeneficiaryRequestDTO data) {
        List<Beneficiary> beneficiaries = beneficiaryService.convertToEntity(data.getBeneficiaries());
        beneficiaryService.saveBeneficiaries(beneficiaries);
        return new BeneficiaryResponseDTO("00", "Success", data.getRequestId());
    }

    @PostMapping(path = "update-beneficiary-details",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BeneficiaryResponseDTO updateBeneficiaryDetails(@RequestBody BeneficiaryRequestDTO data) {
        List<Beneficiary> beneficiaries = beneficiaryService.convertToEntity(data.getBeneficiaries());
        beneficiaryService.updateBeneficiaries(beneficiaries);
        return new BeneficiaryResponseDTO("00", "Success", data.getRequestId());
    }

}
