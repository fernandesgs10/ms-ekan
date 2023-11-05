package br.com.gateway.buy.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tb_contact")
public class ContactEntity extends CreateUpdateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_contact")
    private Long coSeqContact;

    @Column(name = "nm_telephone", length = 15)
    private String nmTelephone;

    @Column(name = "nm_cellphone", nullable = false, length = 15)
    private String nmCellphone;

    @Column(name = "nm_email", nullable = false, length = 30)
    private String nmEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_seq_client")
    @JsonBackReference
    @ToString.Exclude
    private ClientEntity clientEntity;

}