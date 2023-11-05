package br.com.gateway.buy.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class CreateUpdateEntity {

    @Column(name = "nm_created", nullable = false, updatable = false)
    private String nmCreated;

    @Column(name = "dt_created", nullable = false, updatable = false)
    private LocalDateTime dtCreated;

    @Column(name = "nm_edited")
    private String nmEdited;

    @Column(name = "dt_edited")
    private LocalDateTime dtEdited;

}
