package com.waterpurifier;

import com.waterpurifier.dao.AuthDao;
import com.waterpurifier.dao.FeedbackDao;
import com.waterpurifier.dao.UserDao;
import com.waterpurifier.model.Auth;
import com.waterpurifier.model.Result;
import com.waterpurifier.model.User;
import com.waterpurifier.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthDao authDao;

    @Autowired
    FeedbackDao feedbackDao;


    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result test() {

//        Auth auth = new Auth();
//        auth.tel = "13913913999";
//        auth.code = "123456";
//        auth.created = Double.valueOf(new Date().getTime());
//        authDao.insert(auth);

//        String tel = "13913913999";
//        String code = "112233";
//
//        Auth auth = authDao.findByTel(tel);
//
//        double created = auth.created;
//        if(new Date().getTime() - created > 1800000) {
//            authDao.update(auth._id, code, new Date().getTime());
//            //Common.sendMessage(tel, code);
//            return new Result(1, null, null);
//        } else {
//            return new Result(-1, "验证码未过期,请勿频繁请求验证码.", null);
//        }

//        User user = new User();
//        user.tel = "13513513555";
//        user.gender = 0;
//        user.amount = new BigDecimal(305.20).setScale(2, BigDecimal.ROUND_HALF_UP);
//        user.name = "my name 3";
//        user.password = Common.getMD5("passw0rd");
//        userDao.insert(user);

//        User user = userDao.login("1111111", "888888");
        User user = userDao.findByTel("18118438026");

        return new Result(1, null, user);
    }

///////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        //System.out.println(Common.getMD5("888888"));

        for(int i=0; i<1000; i++) {
            System.out.println(Common.getRandom());
        }
    }
}
