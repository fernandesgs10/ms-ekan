package br.com.gateway.buy.product.entity;

import br.com.gateway.buy.product.enums.TypeDocumentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tb_documentary")
public class DocumentEntity extends CreateUpdateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_documentary")
    private Long coSeqDocumentary;

    @Column(name = "nm_description", length = 200)
    private String nmDescription;

    @Column(name = "tp_document", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TypeDocumentEnum tpDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_seq_beneficiary")
    @JsonBackReference
    @ToString.Exclude
    private BeneficiaryEntity beneficiaryEntity;

}