package top.cloudos.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/7 17:08
 * @description
 **/
@SpringBootApplication
@MapperScan(basePackages = "top.cloudos.**.mapper")
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}