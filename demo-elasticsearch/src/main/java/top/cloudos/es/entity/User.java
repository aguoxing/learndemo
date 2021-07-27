package top.cloudos.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gx
 * @date 2021/7/10 11:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "userDoc",shards = 2, replicas = 2)
public class User implements Serializable {
    @Field
    private String id;
    @Field
    private String name;
    private String age;
    private String sex;
    @Field
    private String phoneNumber;
    @Field
    private String email;
    private Date createTime;
}
