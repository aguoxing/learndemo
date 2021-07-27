package top.cloudos.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import top.cloudos.es.entity.User;
import top.cloudos.es.mapper.UserMapper;
import top.cloudos.es.repository.UserRepository;
import top.cloudos.es.service.IUserService;

/**
 * @author gx
 * @date 2021/7/10 11:28
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void deleteIndex(String index) {
//        elasticsearchTemplate.delete();
    }
}
