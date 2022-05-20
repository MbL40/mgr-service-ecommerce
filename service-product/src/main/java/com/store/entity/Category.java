/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

/**
 *
 * @author Herle
 */
@Entity
@Table(name = "tbl_categories")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
// Libreria Lombok para métodos comunes como get, set, toString, equals, etc.
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
 
}
