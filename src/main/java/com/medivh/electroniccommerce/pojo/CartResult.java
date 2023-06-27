package com.medivh.electroniccommerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResult {
    private int id;
    private int userId;
    private int goodId;
    private String name;
    private String author;
    private float price;
    private String introduce;
    private String category;
    private String img;
    private String detailImg;
}
