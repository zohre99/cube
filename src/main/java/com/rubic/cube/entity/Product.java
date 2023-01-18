package com.rubic.cube.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "PRODUCT")
@Entity
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK", nullable = false)
    private String stock;

}
