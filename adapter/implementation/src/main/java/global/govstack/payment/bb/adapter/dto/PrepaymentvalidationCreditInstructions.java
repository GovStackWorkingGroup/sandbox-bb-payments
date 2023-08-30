package global.govstack.payment.bb.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

/** PrepaymentvalidationCreditInstructions */
@Validated
@jakarta.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
public class PrepaymentvalidationCreditInstructions {
  @JsonProperty("InstructionID")
  private String instructionID = null;

  @JsonProperty("PayeeFunctionalID")
  private String payeeFunctionalID = null;

  @JsonProperty("Amount")
  private Float amount = null;

  @JsonProperty("Currency")
  private String currency = null;

  @JsonProperty("Narration")
  private String narration = null;

  public PrepaymentvalidationCreditInstructions instructionID(String instructionID) {
    this.instructionID = instructionID;
    return this;
  }

  /**
   * Get instructionID
   *
   * @return instructionID
   */
  @ApiModelProperty(value = "")
  @Size(min = 16, max = 16)
  public String getInstructionID() {
    return instructionID;
  }

  public void setInstructionID(String instructionID) {
    this.instructionID = instructionID;
  }

  public PrepaymentvalidationCreditInstructions payeeFunctionalID(String payeeFunctionalID) {
    this.payeeFunctionalID = payeeFunctionalID;
    return this;
  }

  /**
   * Get payeeFunctionalID
   *
   * @return payeeFunctionalID
   */
  @ApiModelProperty(value = "")
  @Size(min = 20, max = 20)
  public String getPayeeFunctionalID() {
    return payeeFunctionalID;
  }

  public void setPayeeFunctionalID(String payeeFunctionalID) {
    this.payeeFunctionalID = payeeFunctionalID;
  }

  public PrepaymentvalidationCreditInstructions amount(Float amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   *
   * @return amount
   */
  @ApiModelProperty(value = "")
  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public PrepaymentvalidationCreditInstructions currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   *
   * @return currency
   */
  @ApiModelProperty(value = "")
  @Size(min = 3, max = 3)
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PrepaymentvalidationCreditInstructions narration(String narration) {
    this.narration = narration;
    return this;
  }

  /**
   * Get narration
   *
   * @return narration
   */
  @ApiModelProperty(value = "")
  @Size(max = 50)
  public String getNarration() {
    return narration;
  }

  public void setNarration(String narration) {
    this.narration = narration;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrepaymentvalidationCreditInstructions prepaymentvalidationCreditInstructions =
        (PrepaymentvalidationCreditInstructions) o;
    return Objects.equals(this.instructionID, prepaymentvalidationCreditInstructions.instructionID)
        && Objects.equals(
            this.payeeFunctionalID, prepaymentvalidationCreditInstructions.payeeFunctionalID)
        && Objects.equals(this.amount, prepaymentvalidationCreditInstructions.amount)
        && Objects.equals(this.currency, prepaymentvalidationCreditInstructions.currency)
        && Objects.equals(this.narration, prepaymentvalidationCreditInstructions.narration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instructionID, payeeFunctionalID, amount, currency, narration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrepaymentvalidationCreditInstructions {\n");

    sb.append("    instructionID: ").append(toIndentedString(instructionID)).append("\n");
    sb.append("    payeeFunctionalID: ").append(toIndentedString(payeeFunctionalID)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    narration: ").append(toIndentedString(narration)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
