package top.cloudos.rpc.simple.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 14:53
 * @description
 **/
@Data
@AllArgsConstructor
public class Product implements Serializable {
    private Integer id;
    private String name;
}
