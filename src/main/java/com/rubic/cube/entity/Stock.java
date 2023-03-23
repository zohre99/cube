package com.rubic.cube.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "STOCK")
@Data
public class Stock extends BaseEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "AVAILABLE_COUNT")
    private Integer availableCount;

}
