package com.after.yom.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "menu",schema = "restaurant_usecase")
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id" ,referencedColumnName = "id")
    Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    List<MenuItem> menuItemList;
}
