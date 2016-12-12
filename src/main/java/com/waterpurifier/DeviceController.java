package com.waterpurifier;

import com.waterpurifier.dao.DeviceDao;
import com.waterpurifier.model.Device;
import com.waterpurifier.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceDao deviceDao;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Device> findAll() {

        return deviceDao.findAll(null, "devices");
    }
}
