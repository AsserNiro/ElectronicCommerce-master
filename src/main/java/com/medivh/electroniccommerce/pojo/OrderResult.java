package com.medivh.electroniccommerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult {
    private int id;
    private int userId;
    private int goodsId;
    private String name;
    private String author;
    private float price;
    private String introduce;
    private String category;
    private String img;
    private String detailImg;
    private String time;
    private String cno;
}
