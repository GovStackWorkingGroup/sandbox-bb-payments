package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.UpdateBeneficiaryDetailsApi;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.UpdatebeneficiarydetailsBody;
import global.govstack.payment.bb.adapter.service.BeneficiaryService;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateBeneficiaryDetailsApiController implements UpdateBeneficiaryDetailsApi {

  private final BeneficiaryService registerBeneficiaryService;

  public UpdateBeneficiaryDetailsApiController(BeneficiaryService registerBeneficiaryService) {
    this.registerBeneficiaryService = registerBeneficiaryService;
  }

  @GetMapping("/adapter-health")
  public String getHealth() {
    return registerBeneficiaryService.health();
  }

  public PaymentResponseDTO updateBeneficiaryDetailsPost(
      @ApiParam(value = "", required = true) @Valid @RequestBody
          UpdatebeneficiarydetailsBody body) {
    return registerBeneficiaryService.update(body);
  }
}
