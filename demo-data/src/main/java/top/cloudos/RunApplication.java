package top.cloudos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gx
 * @date 2021/4/28 14:52
 **/
@SpringBootApplication
@MapperScan(basePackages = {"top.cloudos.**.mapper"})
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }
}
