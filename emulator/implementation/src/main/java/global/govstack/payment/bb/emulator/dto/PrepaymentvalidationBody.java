package global.govstack.payment.bb.emulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

/** PrepaymentvalidationBody */
@Validated
@jakarta.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
public class PrepaymentvalidationBody {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("SourceBBID")
  private String sourceBBID = null;

  @JsonProperty("BatchID")
  private String batchID = null;

  @JsonProperty("CreditInstructions")
  @Valid
  private List<PrepaymentvalidationCreditInstructions> creditInstructions = null;

  public PrepaymentvalidationBody requestID(String requestID) {
    this.requestID = requestID;
    return this;
  }

  /**
   * Get requestID
   *
   * @return requestID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getRequestID() {
    return requestID;
  }

  public void setRequestID(String requestID) {
    this.requestID = requestID;
  }

  public PrepaymentvalidationBody sourceBBID(String sourceBBID) {
    this.sourceBBID = sourceBBID;
    return this;
  }

  /**
   * Get sourceBBID
   *
   * @return sourceBBID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getSourceBBID() {
    return sourceBBID;
  }

  public void setSourceBBID(String sourceBBID) {
    this.sourceBBID = sourceBBID;
  }

  public PrepaymentvalidationBody batchID(String batchID) {
    this.batchID = batchID;
    return this;
  }

  /**
   * Get batchID
   *
   * @return batchID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getBatchID() {
    return batchID;
  }

  public void setBatchID(String batchID) {
    this.batchID = batchID;
  }

  public PrepaymentvalidationBody creditInstructions(
      List<PrepaymentvalidationCreditInstructions> creditInstructions) {
    this.creditInstructions = creditInstructions;
    return this;
  }

  public PrepaymentvalidationBody addCreditInstructionsItem(
      PrepaymentvalidationCreditInstructions creditInstructionsItem) {
    if (this.creditInstructions == null) {
      this.creditInstructions = new ArrayList<PrepaymentvalidationCreditInstructions>();
    }
    this.creditInstructions.add(creditInstructionsItem);
    return this;
  }

  /**
   * Get creditInstructions
   *
   * @return creditInstructions
   */
  @ApiModelProperty(value = "")
  @Valid
  public List<PrepaymentvalidationCreditInstructions> getCreditInstructions() {
    return creditInstructions;
  }

  public void setCreditInstructions(
      List<PrepaymentvalidationCreditInstructions> creditInstructions) {
    this.creditInstructions = creditInstructions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrepaymentvalidationBody prepaymentvalidationBody = (PrepaymentvalidationBody) o;
    return Objects.equals(this.requestID, prepaymentvalidationBody.requestID)
        && Objects.equals(this.sourceBBID, prepaymentvalidationBody.sourceBBID)
        && Objects.equals(this.batchID, prepaymentvalidationBody.batchID)
        && Objects.equals(this.creditInstructions, prepaymentvalidationBody.creditInstructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, sourceBBID, batchID, creditInstructions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrepaymentvalidationBody {\n");

    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
    sb.append("    sourceBBID: ").append(toIndentedString(sourceBBID)).append("\n");
    sb.append("    batchID: ").append(toIndentedString(batchID)).append("\n");
    sb.append("    creditInstructions: ").append(toIndentedString(creditInstructions)).append("\n");
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
