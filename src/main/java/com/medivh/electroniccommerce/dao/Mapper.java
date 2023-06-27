package com.medivh.electroniccommerce.dao;

import com.medivh.electroniccommerce.domain.Goods;
import com.medivh.electroniccommerce.domain.User;
import com.medivh.electroniccommerce.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface Mapper {

    public List<User> selectUserByUsername(@Param("username")String username);
    public List<User> checkUserIncludeTel(@Param("user")User user);
    public void insertUser(@Param("user")User user);
    public List<User> verifyLogin(@Param("user")User user);
    public void modifyPassword(@Param("user")User user);
    public void modifyUser(@Param("user")User user);

    public List<User> selectAllUsers();
    public void deleteUser(@Param("id") int id);
    public List<User> selectUser(@Param("id") int id);
    public List<Goods> selectAllGoods();
    public void deleteGood(@Param("id") int id);
    public List<Goods> selectGoods(@Param("id") int id);
    public void modifyGoods(@Param("goods")Goods goods);

    public List<OrderResult> postAllMyOrder(@Param("userId") int userId);
    public List<CartResult> postAllMyCart(@Param("userId") int userId);

    public void addCart(@Param("userId") int userId,@Param("goodId") int goodId);
    public void deleteCart(@Param("id") int id);
    public void deleteOrder(@Param("id") int id);
    public void addOrder(@Param("userId") int userId,@Param("time") String time,@Param("cno")String cno,@Param("goodId") int goodId);
    public List<Cart> getCartById(@Param("id") int id);
    public void addGood(@Param("good") Good good);
}
