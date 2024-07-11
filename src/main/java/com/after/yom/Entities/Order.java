package com.after.yom.Entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Order",schema = "restaurant_usecase")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id" ,referencedColumnName = "id")
    Restaurant restaurant;


    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    List<OrderItem> orderItemList;
}
