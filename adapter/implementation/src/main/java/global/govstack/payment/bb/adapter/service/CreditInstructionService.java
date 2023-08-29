package global.govstack.payment.bb.adapter.service;

import global.govstack.payment.bb.adapter.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.payment.bb.adapter.dto.BulkpaymentBody;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.PrepaymentvalidationBody;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreditInstructionService {

    private final HttpHeaders httpHeaders;
    private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties;
    private final RestTemplate restTemplate;


    public CreditInstructionService(PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties) {
        this.paymentBBInformationMediatorProperties = paymentBBInformationMediatorProperties;
        this.restTemplate = new RestTemplateBuilder()
                .rootUri(paymentBBInformationMediatorProperties.baseUrl()).build();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Road-Client", paymentBBInformationMediatorProperties.header());

    }

    public String health() {
        return restTemplate.exchange(
                paymentBBInformationMediatorProperties.baseUrl() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
    }

    public PaymentResponseDTO prepaymentValidation(PrepaymentvalidationBody body) {
        try {
            return restTemplate.postForObject(
                    paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.prepaymentValidation(),
                    new HttpEntity<>(body, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    public PaymentResponseDTO bulkPayment(BulkpaymentBody body) {
        try {
            return restTemplate.postForObject(
                    paymentBBInformationMediatorProperties.baseUrl() + paymentBBInformationMediatorProperties.bulkPayment(),
                    new HttpEntity<>(body, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }
}
