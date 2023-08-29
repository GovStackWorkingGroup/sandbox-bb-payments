package global.govstack.payment.bb.adapter.service;

import global.govstack.payment.bb.adapter.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.payment.bb.adapter.dto.BulkpaymentBody;
import global.govstack.payment.bb.adapter.dto.PaymentResponseDTO;
import global.govstack.payment.bb.adapter.dto.PrepaymentvalidationBody;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreditInstructionService {

    private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties;
    private final RestTemplate restTemplate;


    public CreditInstructionService(PaymentBBInformationMediatorProperties paymentBBInformationMediatorProperties) {
        this.paymentBBInformationMediatorProperties = paymentBBInformationMediatorProperties;
        this.restTemplate = new RestTemplateBuilder()
                .rootUri(paymentBBInformationMediatorProperties.baseUrl())
                .defaultHeader("X-Road-Client", paymentBBInformationMediatorProperties.header())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public PaymentResponseDTO prepaymentValidation(PrepaymentvalidationBody body) {
        try {
            return restTemplate.postForObject(paymentBBInformationMediatorProperties.prepaymentValidation(),
                    new HttpEntity<>(body),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    public PaymentResponseDTO bulkPayment(BulkpaymentBody body) {
        try {
            return restTemplate.postForObject(paymentBBInformationMediatorProperties.bulkPayment(),
                    new HttpEntity<>(body),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }
}
