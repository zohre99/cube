package com.rubic.cube.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getCode().equals(product.getCode()) && getName().equals(product.getName()) && getColor().equals(product.getColor()) && Objects.equals(getDescription(), product.getDescription()) && getStock().equals(product.getStock());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getCode(), getName(), getColor(), getDescription(), getStock());
    }
}
