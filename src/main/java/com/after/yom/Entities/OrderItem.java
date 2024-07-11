package com.after.yom.Entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item",schema = "restaurant_usecase")
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Float price;

    @Column
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    Order order;
}
