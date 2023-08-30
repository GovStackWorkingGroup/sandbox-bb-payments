package global.govstack.payment.bb.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

/** RegisterbeneficiaryBody */
@Validated
@jakarta.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
public class RegisterbeneficiaryBody {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("SourceBBID")
  private String sourceBBID = null;

  @JsonProperty("Beneficiaries")
  @Valid
  private List<RegisterbeneficiaryBeneficiaries> beneficiaries = null;

  public RegisterbeneficiaryBody requestID(String requestID) {
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

  public RegisterbeneficiaryBody sourceBBID(String sourceBBID) {
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

  public RegisterbeneficiaryBody beneficiaries(
      List<RegisterbeneficiaryBeneficiaries> beneficiaries) {
    this.beneficiaries = beneficiaries;
    return this;
  }

  public RegisterbeneficiaryBody addBeneficiariesItem(
      RegisterbeneficiaryBeneficiaries beneficiariesItem) {
    if (this.beneficiaries == null) {
      this.beneficiaries = new ArrayList<RegisterbeneficiaryBeneficiaries>();
    }
    this.beneficiaries.add(beneficiariesItem);
    return this;
  }

  /**
   * Get beneficiaries
   *
   * @return beneficiaries
   */
  @ApiModelProperty(value = "")
  @Valid
  public List<RegisterbeneficiaryBeneficiaries> getBeneficiaries() {
    return beneficiaries;
  }

  public void setBeneficiaries(List<RegisterbeneficiaryBeneficiaries> beneficiaries) {
    this.beneficiaries = beneficiaries;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterbeneficiaryBody registerbeneficiaryBody = (RegisterbeneficiaryBody) o;
    return Objects.equals(this.requestID, registerbeneficiaryBody.requestID)
        && Objects.equals(this.sourceBBID, registerbeneficiaryBody.sourceBBID)
        && Objects.equals(this.beneficiaries, registerbeneficiaryBody.beneficiaries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, sourceBBID, beneficiaries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterbeneficiaryBody {\n");

    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
    sb.append("    sourceBBID: ").append(toIndentedString(sourceBBID)).append("\n");
    sb.append("    beneficiaries: ").append(toIndentedString(beneficiaries)).append("\n");
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
