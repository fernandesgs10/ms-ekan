package br.com.gateway.buy.product.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "tb_client")
public class ClientEntity extends CreateUpdateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_client", unique = true, nullable = false)
    private Long coSeqClient;

    @Column(name = "nm_name", nullable = false, length = 80)
    private String nmName;

    @Column(name = "nm_nick_name", length = 80)
    private String nmNickName;

    @Column(name = "dd_birthday", nullable = false, length = 8)
    private LocalDate ddBirthday;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactEntity> contacts;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> address;
}