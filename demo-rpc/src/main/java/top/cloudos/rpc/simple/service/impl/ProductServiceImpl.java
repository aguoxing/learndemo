package top.cloudos.rpc.simple.service.impl;

import top.cloudos.rpc.simple.entity.Product;
import top.cloudos.rpc.simple.service.IProductService;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 14:56
 * @description
 **/
public class ProductServiceImpl implements IProductService {
    @Override
    public Product getProductById(Integer id) {
        //实际上这里应该去查询数据库获得数据，下面简化了
        return new Product(id, "手机");
    }
}
