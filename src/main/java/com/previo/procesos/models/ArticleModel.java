package com.previo.procesos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String code;

    @Column(length = 100)
    private String name;

    @Column(length = 300, nullable = false)
    private String description;

    private Date dateOfRegister;

    @ManyToOne
    private CategoryModel categoryModel;

    @Column(length = 10, nullable = false)
    private Integer stock;

    @Column(length = 20, nullable = false)
    private Float salePrice;

    @Column(length = 20)
    private Float purchasePrice;

}