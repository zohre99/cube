package com.rubic.cube.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = "PRODUCT")
@Entity
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    private Long id;

    @Column(name = "CODE", nullable = false, updatable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK", nullable = false)
    private Long stock;

    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @UpdateTimestamp
    private Date lastModifiedDate;

}
