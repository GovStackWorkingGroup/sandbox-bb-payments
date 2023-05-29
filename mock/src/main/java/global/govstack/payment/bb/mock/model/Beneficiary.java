package global.govstack.payment.bb.mock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beneficiary {
    @JsonProperty("PayeeFunctionalID")
    private String payeeFunctionalID;
    @JsonProperty("PaymentModality")
    private String paymentModality;
    @JsonProperty("FinancialAddress")
    private String financialAddress;

    public String getPayeeFunctionalID() {
        return payeeFunctionalID;
    }

    public void setPayeeFunctionalID(String payeeFunctionalID) {
        this.payeeFunctionalID = payeeFunctionalID;
    }

    public String getPaymentModality() {
        return paymentModality;
    }

    public void setPaymentModality(String paymentModality) {
        this.paymentModality = paymentModality;
    }

    public String getFinancialAddress() {
        return financialAddress;
    }

    public void setFinancialAddress(String financialAddress) {
        this.financialAddress = financialAddress;
    }
}
