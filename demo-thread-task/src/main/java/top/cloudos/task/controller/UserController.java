package top.cloudos.task.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GX
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private DateFormat df = new SimpleDateFormat("hh:ss");

    @RequestMapping("/test")
    public String hello() {
        String ans = df.format(new Date());
        return ans;
    }

}
