package com.medivh.electroniccommerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private int userId;
    private int goodId;
    private String time;
    private String cno;

    public Order(int userId, int goodId, String time, String cno) {
        this.userId = userId;
        this.goodId = goodId;
        this.time = time;
        this.cno = cno;
    }
}
