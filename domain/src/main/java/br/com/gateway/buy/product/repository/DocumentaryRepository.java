package br.com.gateway.buy.product.repository;

import br.com.gateway.buy.product.entity.DocumentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DocumentaryRepository extends JpaRepository<DocumentEntity, Long> {



}
