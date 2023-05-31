package global.govstack.payment.bb.mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import global.govstack.payment.bb.mock.model.Beneficiary;

public class BeneficiaryDTO {
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

    public Beneficiary transformToEntity() {
        return new Beneficiary(this.payeeFunctionalID, this.paymentModality, this.financialAddress);
    }

}
