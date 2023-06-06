package global.govstack.payment.bb.mock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CreditInstruction {

    @Id
    private String instructionID;
    private String payeeFunctionalID;
    private Float amount;
    private String currency;
    private String narration;
    @Column(columnDefinition = "boolean default false")
    private Boolean valid;
    @Column(columnDefinition = "boolean default false")
    private Boolean ordered;

    public CreditInstruction() {
    }

    public CreditInstruction(String instructionID, String payeeFunctionalID, Float amount, String currency, String narration, Boolean valid, Boolean ordered) {
        this.instructionID = instructionID;
        this.payeeFunctionalID = payeeFunctionalID;
        this.amount = amount;
        this.currency = currency;
        this.narration = narration;
        this.valid = valid;
        this.ordered = ordered;
    }

    public String getInstructionID() {
        return instructionID;
    }

    public CreditInstruction setInstructionID(String instructionID) {
        this.instructionID = instructionID;
        return this;
    }

    public String getPayeeFunctionalID() {
        return payeeFunctionalID;
    }

    public CreditInstruction setPayeeFunctionalID(String payeeFunctionalID) {
        this.payeeFunctionalID = payeeFunctionalID;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public CreditInstruction setAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CreditInstruction setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getNarration() {
        return narration;
    }

    public CreditInstruction setNarration(String narration) {
        this.narration = narration;
        return this;
    }

    public Boolean getValid() {
        return valid;
    }

    public CreditInstruction setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public CreditInstruction setOrdered(Boolean ordered) {
        this.ordered = ordered;
        return this;
    }

    public static class CreditInstructionBuilder {
        private String instructionID;
        private String payeeFunctionalID;
        private Float amount;
        private String currency;
        private String narration;
        private Boolean valid = false;
        private Boolean ordered = false;

        public CreditInstructionBuilder setInstructionID(String instructionID) {
            this.instructionID = instructionID;
            return this;
        }

        public CreditInstructionBuilder setPayeeFunctionalID(String payeeFunctionalID) {
            this.payeeFunctionalID = payeeFunctionalID;
            return this;
        }

        public CreditInstructionBuilder setAmount(Float amount) {
            this.amount = amount;
            return this;
        }

        public CreditInstructionBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreditInstructionBuilder setNarration(String narration) {
            this.narration = narration;
            return this;
        }

        public CreditInstructionBuilder setValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public CreditInstructionBuilder setOrdered(Boolean ordered) {
            this.ordered = ordered;
            return this;
        }

        public CreditInstruction build() {
            return new CreditInstruction(instructionID, payeeFunctionalID, amount, currency, narration, valid, ordered);
        }
    }
}
