package com.waterpurifier;

import com.waterpurifier.dao.UserDao;
import com.waterpurifier.model.Result;
import com.waterpurifier.model.User;

import com.waterpurifier.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<User> test(HttpServletRequest request) {

        Map<String,Object> params = new HashMap<String,Object>();
        List<User> users = userDao.findAll(params, "users");
        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map body) {

        String tel = body.get("tel").toString();
        String password = Common.getMD5(body.get("password").toString());

        User user = userDao.login(tel, password);
        if(user != null) {
            return new Result(0, null, user);
        } else {
            return new Result(-1, "输入的手机号或密码错误.", user);
        }
    }
}
