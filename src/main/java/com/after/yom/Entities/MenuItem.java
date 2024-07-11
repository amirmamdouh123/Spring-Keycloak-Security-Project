package com.after.yom.Entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "menu_item",schema = "restaurant_usecase")
@Setter
@Getter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id" ,referencedColumnName = "id")
    Menu menu;

}
