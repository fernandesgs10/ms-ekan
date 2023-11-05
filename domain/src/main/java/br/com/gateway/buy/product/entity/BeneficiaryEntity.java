package br.com.gateway.buy.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_beneficiary")
public class BeneficiaryEntity extends CreateUpdateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_beneficiary")
    private Long coSeqBeneficiary;

    @Column(name = "nm_name", nullable = false, length = 100)
    private String nmName;

    @Column(name = "nm_telephone", length = 20)
    private String nmTelephone;

    @Column(name = "dd_birth_date", length = 8)
    private LocalDate ddBirthDate;

    @OneToMany(mappedBy = "beneficiaryEntity")
    private List<DocumentEntity> listDocuments;


}