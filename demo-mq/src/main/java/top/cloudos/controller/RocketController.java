package top.cloudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cloudos.service.FeePlatMqService;

/**
 * @author gx
 * @date 2021/7/10 9:43
 **/
@RestController
@RequestMapping("/mq")
public class RocketController {
    @Autowired
    private FeePlatMqService feePlatMqService;

    public String send(String msg) {
        feePlatMqService.openAccountMsg(msg);
        return "";
    }
}
