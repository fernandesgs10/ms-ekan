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
@Table(name = "tb_address")
public class AddressEntity extends CreateUpdateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_address", unique = true, nullable = false)
    private Long coSeqAddress;

    @Column(name = "nm_address", nullable = false, length = 100)
    private String nmAddress;

    @Column(name = "nm_address_zip_code", length = 10)
    private String nmAddressZipCode;

    @Column(name = "nm_address_number", length = 5)
    private String nmAddressNumber;

    @Column(name = "nm_address_city_code", length = 20)
    private String nmAddressCityCode;

    @Column(name = "nm_address_complement", length = 10)
    private String nmAddressComplement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_seq_client")
    @JsonBackReference
    @ToString.Exclude
    private ClientEntity clientEntity;

}