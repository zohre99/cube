package com.rubic.cube.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
//@EntityListeners({BaseEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

}
