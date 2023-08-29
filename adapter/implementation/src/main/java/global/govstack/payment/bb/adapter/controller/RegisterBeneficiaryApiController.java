package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.RegisterBeneficiaryApi;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.RegisterbeneficiaryBody;
import global.govstack.payment.bb.adapter.service.BeneficiaryService;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")

@RestController
public class RegisterBeneficiaryApiController implements RegisterBeneficiaryApi {

    private static final Logger log = (Logger) LoggerFactory.getLogger(RegisterBeneficiaryApiController.class);

    @Autowired
    private BeneficiaryService registerBeneficiaryService;

    public PaymentResponseDTO registerBeneficiaryPost(@ApiParam(value = "", required=true ) @Valid @RequestBody RegisterbeneficiaryBody body) {
        return registerBeneficiaryService.register(body);
    }

}
