package global.govstack.payment.bb.emulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

/** BulkpaymentBody */
@Validated
@jakarta.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
public class BulkpaymentBody {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("SourceBBID")
  private String sourceBBID = null;

  @JsonProperty("BatchID")
  private String batchID = null;

  @JsonProperty("CreditInstructions")
  @Valid
  private List<BulkpaymentCreditInstructions> creditInstructions = null;

  public BulkpaymentBody requestID(String requestID) {
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

  public BulkpaymentBody sourceBBID(String sourceBBID) {
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

  public BulkpaymentBody batchID(String batchID) {
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

  public BulkpaymentBody creditInstructions(
      List<BulkpaymentCreditInstructions> creditInstructions) {
    this.creditInstructions = creditInstructions;
    return this;
  }

  public BulkpaymentBody addCreditInstructionsItem(
      BulkpaymentCreditInstructions creditInstructionsItem) {
    if (this.creditInstructions == null) {
      this.creditInstructions = new ArrayList<BulkpaymentCreditInstructions>();
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
  public List<BulkpaymentCreditInstructions> getCreditInstructions() {
    return creditInstructions;
  }

  public void setCreditInstructions(List<BulkpaymentCreditInstructions> creditInstructions) {
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
    BulkpaymentBody bulkpaymentBody = (BulkpaymentBody) o;
    return Objects.equals(this.requestID, bulkpaymentBody.requestID)
        && Objects.equals(this.sourceBBID, bulkpaymentBody.sourceBBID)
        && Objects.equals(this.batchID, bulkpaymentBody.batchID)
        && Objects.equals(this.creditInstructions, bulkpaymentBody.creditInstructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, sourceBBID, batchID, creditInstructions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkpaymentBody {\n");

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
