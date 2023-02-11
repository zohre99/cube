package com.rubic.cube.repository;

import com.rubic.cube.entity.Cart;
import com.rubic.cube.entity.OrderItem;
import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<OrderItem> findByProductAndCart(Product product, Cart cart);

    @Query("SELECT o FROM OrderItem o " +
            "WHERE o.product = :product " +
            "And o.cart.user = :user " +
            "And o.cart.status = com.rubic.cube.constant.CartStatus.PROGRESSING")
    Optional<OrderItem> findActiveItemByProductAndUser(Product product, User user);

//    @Modifying
//    @Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("UPDATE OrderItem o SET o.count = o.count + 1 WHERE o.id = :id")
//    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
//    void incrementOrderItemByOne(@Param("id") Long id);
//
//    @Modifying
//    @Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("UPDATE OrderItem o SET o.count = o.count - 1 WHERE o.id = :id")
//    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
//    void reduceOrderItemByOne(@Param("id") Long id);

}
