package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.RegisterBeneficiaryApi;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.RegisterbeneficiaryBody;
import global.govstack.payment.bb.adapter.service.BeneficiaryService;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterBeneficiaryApiController implements RegisterBeneficiaryApi {

  private final BeneficiaryService registerBeneficiaryService;

  public RegisterBeneficiaryApiController(BeneficiaryService registerBeneficiaryService) {
    this.registerBeneficiaryService = registerBeneficiaryService;
  }

  public PaymentResponseDTO registerBeneficiaryPost(
      @ApiParam(value = "", required = true) @Valid @RequestBody RegisterbeneficiaryBody body) {
    return registerBeneficiaryService.register(body);
  }
}
