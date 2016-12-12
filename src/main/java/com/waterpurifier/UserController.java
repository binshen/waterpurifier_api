package com.waterpurifier;

import com.waterpurifier.dao.AuthDao;
import com.waterpurifier.dao.FeedbackDao;
import com.waterpurifier.dao.UserDao;
import com.waterpurifier.model.Auth;
import com.waterpurifier.model.Feedback;
import com.waterpurifier.model.Result;
import com.waterpurifier.model.User;
import com.waterpurifier.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @Autowired
    FeedbackDao feedbackDao;

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

        Auth auth = authDao.findByTel(tel);
        String code = Common.getRandom();
        if(auth == null) {
            auth = new Auth();
            auth.tel = tel;
            auth.code = code;
            auth.created = Double.valueOf(new Date().getTime());
            authDao.insert(auth);
            if(Common.sendMessage(tel, code)) {
                return new Result(1, null, null);
            } else {
                return new Result(-1, "验证码发送失败，请稍后再试", null);
            }
        } else {
            double created = auth.created;
            if(new Date().getTime() - created > 1800000) {
                authDao.update(auth._id, code, new Date().getTime());
                if(Common.sendMessage(tel, code)) {
                    return new Result(1, null, null);
                } else {
                    return new Result(-1, "验证码发送失败，请稍后再试", null);
                }
            } else {
                return new Result(-1, "验证码未过期,请勿频繁请求验证码.", null);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody Map body) {
        String tel = body.get("tel").toString();
        User user = userDao.findByTel(tel);
        if(user != null) {
            return new Result(-1, "您输入的手机号码已经注册过.", null);
        }

        String code = body.get("code").toString();
        Auth auth = authDao.findByTelAndCode(tel, code);
        if(auth == null) {
            return new Result(-1, "您发送的验证码不正确.", null);
        }

        double create = auth.created;
        if(new Date().getTime() - create > 1800000) {
            return new Result(-1, "您发送的验证码已过期.", null);
        }

        user = new User();
        user.tel = tel;
        user.gender = 1;//Integer.parseInt(body.get("gender").toString());
        user.amount = new BigDecimal(0);//new BigDecimal(body.get("amount").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
        user.name = "test";//body.get("name").toString();
        user.password = Common.getMD5(body.get("password").toString());
        userDao.insert(user);

        return new Result(1, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/forget_password", method = RequestMethod.POST)
    public Result forgetPassword(@RequestBody Map body) {
        String tel = body.get("tel").toString();
        User user = userDao.findByTel(tel);
        if(user == null) {
            return new Result(-1, "您输入的手机号码不存在.", null);
        }

        String code = body.get("code").toString();
        Auth auth = authDao.findByTelAndCode(tel, code);
        if(auth == null) {
            return new Result(-1, "您发送的验证码不正确.", null);
        }

        double create = auth.created;
        if(new Date().getTime() - create > 1800000) {
            return new Result(-1, "您发送的验证码已过期.", null);
        }

        String password = Common.getMD5(body.get("password").toString());
        userDao.changePassword(user._id, password);
        return new Result(1, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/{user_id}/change_password", method = RequestMethod.POST)
    public Result changePassword(@PathVariable("user_id") String id, @RequestBody Map body) {
        String password = Common.getMD5(body.get("password").toString());
        String new_password = Common.getMD5(body.get("new_password").toString());
        User user = userDao.fetchUser(id, password);
        if(user == null) {
            return new Result(-1, "输入的原密码不正确.", null);
        } else {
            userDao.changePassword(id, new_password);
            return new Result(1, null, null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{user_id}/feedback", method = RequestMethod.POST)
    public Result feedback(@PathVariable("user_id") String id, @RequestBody Map body) {
        Feedback feedback = new Feedback();
        feedback.userID = id;
        feedback.feedback = body.get("feedback").toString();
        feedbackDao.insert(feedback);
        return new Result(1, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/{user_id}/update_name", method = RequestMethod.POST)
    public Result updateName(@PathVariable("user_id") String id, @RequestBody Map body) {
        String name = body.get("name").toString();
        userDao.changeName(id, name);
        return new Result(1, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/{user_id}/update_password", method = RequestMethod.POST)
    public Result updatePassword(@PathVariable("user_id") String id, @RequestBody Map body) {
        String password = body.get("password").toString();
        userDao.changePassword(id, password);
        return new Result(1, null, null);
    }
}
