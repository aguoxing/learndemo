package top.cloudos.upload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gx
 * @date 2021/5/13 11:29
 **/
@SpringBootApplication
@MapperScan(basePackages = {"top.cloudos.**.mapper"})
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}