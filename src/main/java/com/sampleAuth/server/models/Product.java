package com.sampleAuth.server.models;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "productType")
    private String productType;


    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;


    @Column(name = "isAvailable")
    private Boolean isAvailable;


    @Column(name = "isOutdated")
    private Boolean isOutdated;

    public Product(Long id, String title, String productType,
                   String description, Double price, Boolean isAvailable,
                   Boolean isOutdated) {
        this.id = id;
        this.title = title;
        this.productType = productType;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.isOutdated = isOutdated;
    }
    public Product(){
        this.id = id;
        this.title = title;
        this.productType = productType;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.isOutdated = isOutdated;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getOutdated() {
        return isOutdated;
    }

    public void setOutdated(Boolean outdated) {
        isOutdated = outdated;
    }
}
