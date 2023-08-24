package global.govstack.payment.bb.adapter.repository;

import global.govstack.payment.bb.adapter.model.CreditInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CreditInstructionRepository extends JpaRepository<CreditInstruction, String> {

    @Query(value = "SELECT * FROM CREDIT_INSTRUCTION WHERE amount = :amount AND ORDERED IS false AND valid = true AND currency = :currency AND instructionId = :instructionId AND narration = :narration AND payee_functionalId = :payeeFunctionalId",
            nativeQuery = true)
    Optional<CreditInstruction> findValidatedCreditInstruction(Float amount, String currency, String instructionId, String narration, String payeeFunctionalId);

}
