package com.waterpurifier;

import com.waterpurifier.dao.AuthDao;
import com.waterpurifier.dao.UserDao;
import com.waterpurifier.model.Auth;
import com.waterpurifier.model.Result;
import com.waterpurifier.model.User;
import com.waterpurifier.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthDao authDao;

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

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout() {

        return new Result(0, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/request_code", method = RequestMethod.POST)
    public Result requestCode(@RequestBody Map body) {

        String tel = body.get("tel").toString();
        if(StringUtils.isEmpty(tel)) {
            return new Result(-1, "系统错误，请重试.", null);
        }

        Auth auth = authDao.findOne(tel);
        String code = Common.getRandom();
        if(auth == null) {
            auth = new Auth();
            auth.tel = tel;
            auth.code = code;
            auth.created = Double.valueOf(new Date().getTime());
            authDao.insert(auth);
            Common.sendMessage(tel, code);
            return new Result(1, null, null);
        } else {
            double created = auth.created;
            if(new Date().getTime() - created > 1800000) {
                authDao.update(auth._id, code, new Date().getTime());
                Common.sendMessage(tel, code);
                return new Result(1, null, null);
            } else {
                return new Result(-1, "验证码未过期,请勿频繁请求验证码.", null);
            }
        }
    }
}
