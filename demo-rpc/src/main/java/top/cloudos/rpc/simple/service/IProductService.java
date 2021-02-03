package top.cloudos.rpc.simple.service;

import top.cloudos.rpc.simple.entity.Product;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 14:56
 * @description
 **/
public interface IProductService {
    /**
     * 通过id获取
     * @param id
     * @return
     */
    Product getProductById(Integer id);
}
