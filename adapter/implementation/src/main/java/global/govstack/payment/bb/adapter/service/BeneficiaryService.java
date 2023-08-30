package global.govstack.payment.bb.adapter.service;

import global.govstack.payment.bb.adapter.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.RegisterbeneficiaryBody;
import global.govstack.payment.bb.adapter.dto.UpdatebeneficiarydetailsBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class BeneficiaryService {
  private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties;
  private final RestTemplate restTemplate;

  public BeneficiaryService(
      PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties) {
    this.paymentBBInformationMediatorProperties = paymentBBInformationMediatorProperties;
    this.restTemplate =
        new RestTemplateBuilder()
            .rootUri(paymentBBInformationMediatorProperties.baseUrl())
            .defaultHeader("X-Road-Client", paymentBBInformationMediatorProperties.header())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }

  public String health() {
    return restTemplate.exchange("/actuator/health", HttpMethod.GET, null, String.class).getBody();
  }

  public PaymentResponseDTO register(RegisterbeneficiaryBody body) {
    try {
      return restTemplate.postForObject(
          paymentBBInformationMediatorProperties.registerBeneficiary(),
          new HttpEntity<>(body),
          PaymentResponseDTO.class);
    } catch (Exception ex) {
      log.error("Request failed", ex);
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  public PaymentResponseDTO update(UpdatebeneficiarydetailsBody body) {
    try {
      return restTemplate.postForObject(
          paymentBBInformationMediatorProperties.updateBeneficiary(),
          new HttpEntity<>(body),
          PaymentResponseDTO.class);
    } catch (Exception ex) {
      log.error("Request failed", ex);
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }
}
