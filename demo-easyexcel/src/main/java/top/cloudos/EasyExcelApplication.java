package top.cloudos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gx
 * @date 2021/4/19 17:22
 **/
@SpringBootApplication
@MapperScan(basePackages = {"top.cloudos.**.mapper"})
public class EasyExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyExcelApplication.class, args);
    }
}
