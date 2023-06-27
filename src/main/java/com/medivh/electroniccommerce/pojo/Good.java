package com.medivh.electroniccommerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private int id;
    private String name;
    private String author;
    private float price;
    private String introduce;
    private String category;
    private String img;
    private String detailImg;

    public Good(String name, String author, float price, String introduce, String category, String img, String detailImg) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.introduce = introduce;
        this.category = category;
        this.img = img;
        this.detailImg = detailImg;
    }
}
