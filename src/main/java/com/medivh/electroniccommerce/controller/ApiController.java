package com.medivh.electroniccommerce.controller;

import com.google.gson.Gson;
import com.medivh.electroniccommerce.dao.Mapper;
import com.medivh.electroniccommerce.domain.Goods;
import com.medivh.electroniccommerce.domain.User;
import com.medivh.electroniccommerce.pojo.*;
import com.medivh.electroniccommerce.request.*;
import com.medivh.electroniccommerce.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ApiController {
    Gson gson = new Gson();
    Object lock = new Object();
    @Autowired
    Mapper mapper;

    /**
     * 用户注册
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public String register(RegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String telephone = request.getTelephone();
        User user = new User(username,password,telephone);
        synchronized (lock) {
            List<User> list = mapper.selectUserByUsername(user.getUsername());
            if (list.size() == 0) {
                mapper.insertUser(user);
                return gson.toJson(new CommonResult(1));
            } else {
                return gson.toJson(new CommonResult(301));
            }
        }

    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = new User(username,password);
        List<User> list = mapper.verifyLogin(user);
        if (list.size() != 0) {
            return gson.toJson(new CommonResult(list.get(0).getId()));
        } else {
            return gson.toJson(new CommonResult(-1));
        }
    }

    /**
     * 用户忘记密码时修改密码
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/forgetPassword")
    public String forgetPassword(ForgetPasswordRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String telephone = request.getTelephone();
        User user = new User(username,password,telephone);
        List<User> list = mapper.checkUserIncludeTel(user);
        if (list.size() == 0) {
            return gson.toJson(new CommonResult(301));
        } else {
            mapper.modifyPassword(user);
            return gson.toJson(new CommonResult(1));
        }
    }

    /**
     * 管理员登录
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/adminLogin")
    public String adminLogin(LoginRequest request) {
        if (request.getUsername().equals("admin") && request.getPassword().equals("123456")) {
            return gson.toJson(new CommonResult(1));
        } else {
            return gson.toJson(new CommonResult(301));
        }
    }

    @ResponseBody
    @PostMapping("/usersManage")
    public List<User> usersManage() {
        return mapper.selectAllUsers();
    }

    @ResponseBody
    @PostMapping("/editUsersInfo")
    public User editUsersInfo(int id) {
        List<User> list = mapper.selectUser(id);
        if (list.size() == 0) {
            return new User(-1,null,null,null);
        }
        return list.get(0);
    }

    @ResponseBody
    @PostMapping("/editUsersInfo1")
    public CommonResult editUsersInfo1(User user) {
        try {
            List<User> list = mapper.selectUser(user.getId());
            if (list.size() == 0) {
                return new CommonResult(-1);
            }
            if (user.getPassword() == null) user.setPassword(list.get(0).getPassword());
            mapper.modifyUser(user);
            return new CommonResult(1);
        } catch (Exception e) {
            return new CommonResult(-1);
        }

    }

    /**
     * 查询所有用户数据到管理员的用户界面上
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/usersManage")
    public List<User> deleteUsers(int id) {
        mapper.deleteUser(id);
        return mapper.selectAllUsers();
    }

    /**
     * 查询所有商品信息到管理员的商品信息管理界面上
     * @return
     */
    @ResponseBody
    @PostMapping("/goodsManage")
    public List<Goods> goodsManage() {
        return mapper.selectAllGoods();
    }

    /**
     * TODO-
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/goodsManage")
    public List<Goods> goodsManage(int id) {
        mapper.deleteGood(id);
        return mapper.selectAllGoods();
    }

    /**
     * 编辑对应商品信息
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/editGoodsInfo")
    public Goods editGoodsInfo(int id) {
        System.out.println("editGoodsInfo");
        List<Goods> list = mapper.selectGoods(id);
        if (list.size() == 0) {
            return new Goods(-1, null, -1, null, null, null);
        }
        return list.get(0);
    }

    /**
     *
     * @param goods
     * @return
     */
    @ResponseBody
    @PostMapping("/editGoodsInfo1")
    public CommonResult editGoodsInfo1(Goods goods) {
        System.out.println("editGoodsInfo1");
        List<Goods> list = mapper.selectGoods(goods.getId());
        if (list.size() == 0) {
            return new CommonResult(301);
        }
        mapper.modifyGoods(goods);
        return new CommonResult(1);
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/postGoodInfo")
    public Goods postGoodInfo(int id) {
        System.out.println("postGoodInfo");
        List<Goods> list = mapper.selectGoods(id);
        if (list.size() == 0) {
            return new Goods();
        } else return list.get(0);
    }

    @ResponseBody
    @PostMapping("/postOrder")
    public List<OrderResult> postAllMyOrder(int id) {
        List<OrderResult> list = mapper.postAllMyOrder(id);
        return list;
    }

    @ResponseBody
    @PostMapping("/postCart")
    public List<CartResult> postAllMyCart(int id) {
        List<CartResult> list = mapper.postAllMyCart(id);
        return list;
    }

    @ResponseBody
    @PostMapping("/addCart")//传参 userId  与  goodId
    public CommonResult addCart(CartRequest request) {
        try {
            mapper.addCart(request.getUserId(), request.getGoodId());
            return new CommonResult(1);
        } catch (Exception e) {
            return new CommonResult(-1);
        }
    }

    @ResponseBody
    @DeleteMapping("/deleteCart")//cartId
    public List<CartResult> deleteCart(CartRequest request) {
        try {
            mapper.deleteCart(request.getCartId());
            return mapper.postAllMyCart(request.getUserId());
        } catch (Exception e) {
            return mapper.postAllMyCart(request.getUserId());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/personInfo")
    public User personInfo(int id) {
        List<User> list = mapper.selectUser(id);
        if (list.size() == 0) {
            return new User();
        } else return list.get(0);
    }

    @ResponseBody
    @DeleteMapping("/deleteOrder")//orderId
    public List<OrderResult> deleteOrder(CartRequest request) {
        try {
            mapper.deleteOrder(request.getOrderId());
            return mapper.postAllMyOrder(request.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return mapper.postAllMyOrder(request.getUserId());
        }
    }

    @ResponseBody
    @PostMapping("/pay")//cartId
    public CommonResult pay(CartRequest request) {
        try {
            List<Cart> list = mapper.getCartById(request.getCartId());
            if (list.size() == 0) throw new Exception("id不存在");
            Cart cart = list.get(0);
            String[] ss = getTimeCno();
            Order t = new Order(cart.getUserId(), cart.getGoodId(), ss[0], ss[1]);
            mapper.addOrder(t.getUserId(), t.getTime(), t.getCno(), t.getGoodId());
            mapper.deleteCart(request.getCartId());
            return new CommonResult(1);
        } catch (Exception e) {
            System.out.println(request);
            e.printStackTrace();
            return new CommonResult(1);
        }
    }

    volatile static int cno = 100000;
    private static final Object lock1 = new Object();

    public static String[] getTimeCno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String s = sdf1.format(date);
        StringBuffer sb = new StringBuffer(sdf.format(date));
        synchronized (lock1) {
            cno++;
            if (cno >= 1000000) cno = 100000;
        }
        sb.append(cno);
        return new String[]{s, sb.toString()};
    }

    @PostMapping("/addGood")
    public CommonResult addGood(GoodRequest request) {
        try {
            mapper.addGood(new Good(request.getName(),
                    request.getAuthor(),
                    request.getPrice(),
                    request.getIntroduce(),
                    request.getCategory(),
                    request.getImg(),
                    request.getDetailImg()));
            return new CommonResult(1);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(-1);
        }


    }
}
