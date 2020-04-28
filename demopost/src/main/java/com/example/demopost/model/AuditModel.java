package com.example.demopost.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt","updatedAt"},
        allowGetters = true
)
// Nosotros no vamos a enviar los valores para la fecha de creación ni modificación
abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    // Para que el nombre de mi columna no tome el mismo nombre que el atributo
    // Temporal viene de persistence
    @Column(name="create_at", nullable=false, updatable=false)

    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_at", nullable=false)
    @LastModifiedDate
    private Date updatedAt;
}
