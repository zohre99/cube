package com.rubic.cube.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ORDER_ITEM")
@Entity
@Data
public class OrderItem extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_ORDER_ITEM", sequenceName = "SEQ_ORDER_ITEM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_ITEM")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CART_ID", referencedColumnName = "ID")
    private Cart cart;

}
