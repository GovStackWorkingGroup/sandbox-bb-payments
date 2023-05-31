package global.govstack.payment.bb.mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BeneficiaryResponseDTO {
    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("ResponseDescription")
    private String responseDescription;
    @JsonProperty("RequestID")
    private String requestID;

    public BeneficiaryResponseDTO(String responseCode, String responseDescription, String requestID) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.requestID = requestID;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
