//package com.medivh.electroniccommerce.temp;
//
//import com.google.gson.Gson;
//import com.medivh.electroniccommerce.dao.Mapper;
//import com.medivh.electroniccommerce.pojo.*;
//import com.medivh.electroniccommerce.request.*;
//import com.medivh.electroniccommerce.result.CommonResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class ApiController {
//    Gson gson = new Gson();
//    Object lock = new Object();
//    @Autowired
//    Mapper mapper;
//
//    @ResponseBody
//    @PostMapping("/register")
//    public String register(RegisterRequest request) {
//        String username = request.getUsername();
//        String password = request.getPassword();
//        String telephone = request.getTelephone();
//        Account account = new Account(username, password, telephone);
//        synchronized (lock) {
//            List<Account> list = mapper.selectAccountByUsername(account.getUsername());
//            if (list.size() == 0) {
//                mapper.insertAccount(account);
//                return gson.toJson(new CommonResult(1));
//            } else {
//                return gson.toJson(new CommonResult(301));
//            }
//        }
//
//    }
//
//    @ResponseBody
//    @PostMapping("/login")
//    public String login(LoginRequest request) {
//        String username = request.getUsername();
//        String password = request.getPassword();
//
//        Account account = new Account(username, password);
//        List<Account> list = mapper.verifyLogin(account);
//        if (list.size() != 0) {
//            return gson.toJson(new CommonResult(list.get(0).getId()));
//        } else {
//            return gson.toJson(new CommonResult(-1));
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/forgetPassword")
//    public String forgetPassword(ForgetPasswordRequest request) {
//        String username = request.getUsername();
//        String password = request.getPassword();
//        String telephone = request.getTelephone();
//        Account account = new Account(username, password, telephone);
//        List<Account> list = mapper.checkAccountIncludeTel(account);
//        if (list.size() == 0) {
//            return gson.toJson(new CommonResult(301));
//        } else {
//            mapper.modifyPassword(account);
//            return gson.toJson(new CommonResult(1));
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/adminLogin")
//    public String adminLogin(LoginRequest request) {
//        if (request.getUsername().equals("admin") && request.getPassword().equals("123456")) {
//            return gson.toJson(new CommonResult(1));
//        } else {
//            return gson.toJson(new CommonResult(301));
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/usersManage")
//    public List<Account> usersManage() {
//        return mapper.selectAllUsers();
//    }
//
//    @ResponseBody
//    @PostMapping("/editUsersInfo")
//    public Account editUsersInfo(int id) {
//        List<Account> list = mapper.selectUser(id);
//        if (list.size() == 0) {
//            return new Account(-1, "未知", "未知", "未知", "未知", "未知");
//        }
//        return list.get(0);
//    }
//
//    @ResponseBody
//    @PostMapping("/editUsersInfo1")
//    public CommonResult editUsersInfo1(Account account) {
//        try {
//            List<Account> list = mapper.selectUser(account.getId());
//            if (list.size() == 0) {
//                return new CommonResult(-1);
//            }
//            if (account.getPassword() == null) account.setPassword(list.get(0).getPassword());
//            mapper.modifyAccount(account);
//            return new CommonResult(1);
//        } catch (Exception e) {
//            return new CommonResult(-1);
//        }
//
//    }
//
//    @ResponseBody
//    @DeleteMapping("/usersManage")
//    public List<Account> deleteUsers(int id) {
//        mapper.deleteUser(id);
//        return mapper.selectAllUsers();
//    }
//
//    @ResponseBody
//    @PostMapping("/goodsManage")
//    public List<Good> goodsManage() {
//        return mapper.selectAllGoods();
//    }
//
//    @ResponseBody
//    @DeleteMapping("/goodsManage")
//    public List<Good> goodsManage(int id) {
//        mapper.deleteGood(id);
//        return mapper.selectAllGoods();
//    }
//
//    @ResponseBody
//    @PostMapping("/editGoodsInfo")
//    public Good editGoodsInfo(int id) {
//        List<Good> list = mapper.selectGood(id);
//        if (list.size() == 0) {
//            return new Good(-1, null, null, -1, null, null, null, null);
//        }
//        return list.get(0);
//    }
//
//    @ResponseBody
//    @PostMapping("/editGoodsInfo1")
//    public CommonResult editGoodsInfo1(Good good) {
//        List<Good> list = mapper.selectGood(good.getId());
//        if (list.size() == 0) {
//            return new CommonResult(301);
//        }
//        mapper.modifyGood(good);
//        return new CommonResult(1);
//    }
//
//    @ResponseBody
//    @PostMapping("/postGoodInfo")
//    public Good postGoodInfo(int id) {
//        List<Good> list = mapper.selectGood(id);
//        if (list.size() == 0) {
//            return new Good();
//        } else return list.get(0);
//    }
//
//    @ResponseBody
//    @PostMapping("/postOrder")
//    public List<OrderResult> postAllMyOrder(int id) {
//        List<OrderResult> list = mapper.postAllMyOrder(id);
//        return list;
//    }
//
//    @ResponseBody
//    @PostMapping("/postCart")
//    public List<CartResult> postAllMyCart(int id) {
//        List<CartResult> list = mapper.postAllMyCart(id);
//        return list;
//    }
//
//    @ResponseBody
//    @PostMapping("/addCart")//传参 userId  与  goodId
//    public CommonResult addCart(CartRequest request) {
//        try {
//            mapper.addCart(request.getUserId(), request.getGoodId());
//            return new CommonResult(1);
//        } catch (Exception e) {
//            return new CommonResult(-1);
//        }
//    }
//
//    @ResponseBody
//    @DeleteMapping("/deleteCart")//cartId
//    public List<CartResult> deleteCart(CartRequest request) {
//        try {
//            mapper.deleteCart(request.getCartId());
//            return mapper.postAllMyCart(request.getUserId());
//        } catch (Exception e) {
//            return mapper.postAllMyCart(request.getUserId());
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/personInfo")
//    public Account personInfo(int id) {
//        List<Account> list = mapper.selectUser(id);
//        if (list.size() == 0) {
//            return new Account();
//        } else return list.get(0);
//    }
//
//    @ResponseBody
//    @DeleteMapping("/deleteOrder")//orderId
//    public List<OrderResult> deleteOrder(CartRequest request) {
//        try {
//            mapper.deleteOrder(request.getOrderId());
//            return mapper.postAllMyOrder(request.getUserId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return mapper.postAllMyOrder(request.getUserId());
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/pay")//cartId
//    public CommonResult pay(CartRequest request) {
//        try {
//            List<Cart> list = mapper.getCartById(request.getCartId());
//            if (list.size() == 0) throw new Exception("id不存在");
//            Cart cart = list.get(0);
//            String[] ss = getTimeCno();
//            Order t = new Order(cart.getUserId(), cart.getGoodId(), ss[0], ss[1]);
//            mapper.addOrder(t.getUserId(), t.getTime(), t.getCno(), t.getGoodId());
//            mapper.deleteCart(request.getCartId());
//            return new CommonResult(1);
//        } catch (Exception e) {
//            System.out.println(request);
//            e.printStackTrace();
//            return new CommonResult(1);
//        }
//    }
//
//    volatile static int cno = 100000;
//    private static final Object lock1 = new Object();
//
//    public static String[] getTimeCno() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        Date date = new Date();
//        String s = sdf1.format(date);
//        StringBuffer sb = new StringBuffer(sdf.format(date));
//        synchronized (lock1) {
//            cno++;
//            if (cno >= 1000000) cno = 100000;
//        }
//        sb.append(cno);
//        return new String[]{s, sb.toString()};
//    }
//
//    @PostMapping("/addGood")
//    public CommonResult addGood(GoodRequest request) {
//        try {
//            mapper.addGood(new Good(request.getName(),
//                    request.getAuthor(),
//                    request.getPrice(),
//                    request.getIntroduce(),
//                    request.getCategory(),
//                    request.getImg(),
//                    request.getDetailImg()));
//            return new CommonResult(1);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new CommonResult(-1);
//        }
//
//
//    }
//}
