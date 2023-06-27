package com.medivh.electroniccommerce.controller;

import com.medivh.electroniccommerce.dao.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired
    Mapper mapper;

    @GetMapping("/login")
    public String login() {
        return "登录注册/login";
    }

    @GetMapping("/register")
    public String register() {
        return "登录注册/register";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword() {
        return "登录注册/forgetPassword";
    }

    @GetMapping("/editGoodsInfo")
    public String editGoodsInfo() {
        return "管理员/editGoodsInfo";
    }

    @GetMapping("/editUsersInfo")
    public String editUsersInfo() {
        return "管理员/editUsersInfo";
    }

    @GetMapping("/goodsManage")
    public String goodsManage() {
        return "管理员/goodsManage";
    }

    @GetMapping("/usersManage")
    public String usersManage() {
        return "管理员/usersManage";
    }
    @GetMapping("/goodscar")
    public String goodscar() {
        return "用户/goodscar";
    }
    @GetMapping("/goodsInfo")
    public String goodsInfo() {
        return "用户/goodsInfo";
    }
    @GetMapping("/goodsShow")
    public String goodsShow() {
        return "用户/goodsShow";
    }
    @GetMapping("/myGoods")
    public String myGoods() {
        return "用户/myGoods";
    }
    @GetMapping("/personInfo")
    public String personInfo() {
        return "用户/personInfo";
    }
    @GetMapping("/selectGoods")
    public String selectGoods(){
        return "用户/selectGoods";
    }

}

