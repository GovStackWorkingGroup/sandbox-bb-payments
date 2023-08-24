package global.govstack.payment.bb.adapter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment.bb.im")
public record PaymentBBInformationMediatorProperties(String baseUrl, String header, String registerBeneficiary, String updateBeneficiary, String bulkPayment, String prepaymentValidation) {}