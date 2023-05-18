package com.rubic.cube.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
//@EntityListeners({BaseEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Column(name = "CREATED_DATE", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
