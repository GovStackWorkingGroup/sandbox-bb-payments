package global.govstack.payment.bb.mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BeneficiaryRequestDTO {
    @JsonProperty("RequestID")
    private String requestId;
    @JsonProperty("SourceBBID")
    private String sourceBBID;
    @JsonProperty("Beneficiaries")
    private List<BeneficiaryDTO> beneficiaries;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSourceBBID() {
        return sourceBBID;
    }

    public void setSourceBBID(String sourceBBID) {
        this.sourceBBID = sourceBBID;
    }

    public List<BeneficiaryDTO> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryDTO> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
