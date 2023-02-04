package com.rubic.cube.entity;

import com.rubic.cube.constant.CartStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "CART")
@Entity
@Data
//@EntityListeners({CartListener.class})
public class Cart extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_CART", sequenceName = "SEQ_CART", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CART")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATUS")
    private CartStatus status;

    @Transient
    List<OrderItem> orderItems;

}
