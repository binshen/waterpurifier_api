package com.waterpurifier;

import com.waterpurifier.dao.UserDao;
import com.waterpurifier.dao.VersionDao;
import com.waterpurifier.model.Result;
import com.waterpurifier.model.User;

import com.waterpurifier.model.Version;
import com.waterpurifier.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    VersionDao versionDao;

    @ResponseBody
    @RequestMapping(value = "/version/{type}", method = RequestMethod.GET)
    public Version getVersion(@PathVariable("type") int type) {

        return versionDao.findByType(type);
    }

////////////////////////////////////////////////////////////////

    @Autowired
    UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<User> test(HttpServletRequest request) {

        return userDao.findAll();
    }


}
