package br.com.gateway.buy.product.repository;

import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, Long> {

    @Query("select b from BeneficiaryEntity b")
    Page<BeneficiaryEntity> findBeneficiaryAll(Pageable pageable);

    @Query("select b from BeneficiaryEntity b where b.coSeqBeneficiary = :coSeqBeneficiary")
    Optional<BeneficiaryEntity> findBeneficiaryByCoSeqBeneficiary(@Param("coSeqBeneficiary") Integer coSeqBeneficiary);

}
