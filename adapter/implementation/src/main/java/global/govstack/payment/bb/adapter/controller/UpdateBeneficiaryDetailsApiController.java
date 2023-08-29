package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.UpdateBeneficiaryDetailsApi;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.UpdatebeneficiarydetailsBody;
import global.govstack.payment.bb.adapter.service.BeneficiaryService;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
@RestController
public class UpdateBeneficiaryDetailsApiController implements UpdateBeneficiaryDetailsApi {

    private static final Logger log = LoggerFactory.getLogger(UpdateBeneficiaryDetailsApiController.class);

    @Autowired
    private BeneficiaryService registerBeneficiaryService;

    @GetMapping("/adapter-health")
    public String getHealth() {
        return registerBeneficiaryService.health();
    }

    public PaymentResponseDTO updateBeneficiaryDetailsPost(@ApiParam(value = "", required=true ) @Valid @RequestBody UpdatebeneficiarydetailsBody body) {
        return registerBeneficiaryService.update(body);
    }

}
