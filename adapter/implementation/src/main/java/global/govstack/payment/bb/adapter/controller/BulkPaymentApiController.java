package global.govstack.payment.bb.adapter.controller;

import global.govstack.payment.bb.adapter.api.BulkPaymentApi;
import global.govstack.payment.bb.adapter.dto.BulkpaymentBody;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.service.CreditInstructionService;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkPaymentApiController implements BulkPaymentApi {

  private final CreditInstructionService creditInstructionService;

  public BulkPaymentApiController(CreditInstructionService creditInstructionService) {
    this.creditInstructionService = creditInstructionService;
  }

  public PaymentResponseDTO bulkPaymentPost(
      @ApiParam(value = "", required = true) @Valid @RequestBody BulkpaymentBody body) {
    return creditInstructionService.bulkPayment(body);
  }
}
