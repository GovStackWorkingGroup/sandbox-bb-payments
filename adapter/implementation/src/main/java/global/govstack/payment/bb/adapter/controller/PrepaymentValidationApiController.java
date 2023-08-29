package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.PrepaymentValidationApi;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.PrepaymentvalidationBody;
import global.govstack.payment.bb.adapter.service.CreditInstructionService;
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
public class PrepaymentValidationApiController implements PrepaymentValidationApi {

    private static final Logger log = LoggerFactory.getLogger(PrepaymentValidationApiController.class);

    @Autowired
    private CreditInstructionService creditInstructionService;

    public PaymentResponseDTO prepaymentValidationPost(@ApiParam(value = "", required=true ) @Valid @RequestBody PrepaymentvalidationBody body) {
        return creditInstructionService.prepaymentValidation(body);
    }
}
