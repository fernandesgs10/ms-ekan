package br.com.gateway.buy.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreateUpdateEntity {

    @Column(name = "nm_created", nullable = false, updatable = false)
    private String nmCreated;

    @CreationTimestamp
    @Column(name = "dt_created", nullable = false, updatable = false)
    private Instant dtCreated;

    @Column(name = "nm_edited")
    private String nmEdited;

    @UpdateTimestamp
    @Column(name = "dt_edited", insertable = false)
    private Instant dtEdited;

}
