package global.govstack.payment.bb.emulator.repository;

import global.govstack.payment.bb.emulator.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, String> {

  boolean existsBeneficiaryByPayeeFunctionalID(String payeeFunctionalID);
}
