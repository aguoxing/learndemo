package top.cloudos.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.cloudos.es.entity.User;

/**
 * @author gx
 * @date 2021/7/10 11:53
 **/
public interface UserRepository extends ElasticsearchRepository<User, String> {

}
