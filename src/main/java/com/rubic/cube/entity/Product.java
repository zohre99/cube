package com.rubic.cube.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "PRODUCT", uniqueConstraints = @UniqueConstraint(columnNames = {"CODE", "NAME", "COLOR"}))
@Entity
@Data
public class Product extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    private Long id;

    @Column(name = "CODE", nullable = false, updatable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stock stock;

}
