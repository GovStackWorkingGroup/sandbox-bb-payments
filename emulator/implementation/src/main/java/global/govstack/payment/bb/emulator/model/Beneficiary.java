package global.govstack.payment.bb.emulator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Beneficiary {

  @Id private String payeeFunctionalID;
  private String paymentModality;
  private String financialAddress;

  public Beneficiary() {}

  public Beneficiary(String payeeFunctionalID, String paymentModality, String financialAddress) {
    this.payeeFunctionalID = payeeFunctionalID;
    this.paymentModality = paymentModality;
    this.financialAddress = financialAddress;
  }

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
