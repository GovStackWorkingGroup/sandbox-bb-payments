package global.govstack.payment.bb.mock.controller;

import global.govstack.payment.bb.mock.model.BeneficiaryRequest;
import global.govstack.payment.bb.mock.model.BeneficiaryResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BeneficiaryController {

    @PostMapping(path = "register-beneficiary",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BeneficiaryResponse registerBeneficiary(@RequestBody BeneficiaryRequest data) {
        BeneficiaryResponse response = new BeneficiaryResponse();
        response.setResponseCode("00");
        response.setRequestID(data.getRequestId());
        response.setResponseDescription("Success");
        return response;
    }

    @PostMapping(path = "update-beneficiary-details",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BeneficiaryResponse updateBeneficiaryDetails(@RequestBody BeneficiaryRequest data) {
        BeneficiaryResponse response = new BeneficiaryResponse();
        response.setResponseCode("00");
        response.setRequestID(data.getRequestId());
        response.setResponseDescription("Success");
        return response;
    }

}
