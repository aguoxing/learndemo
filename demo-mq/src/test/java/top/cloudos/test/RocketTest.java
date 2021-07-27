package top.cloudos.test;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.tools.command.SubCommandException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import top.cloudos.entity.User;
import top.cloudos.service.FeePlatMqService;

/**
 * @author gx
 * @date 2021/7/10 9:08
 **/
@SpringBootTest
public class RocketTest {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private FeePlatMqService feePlatMqService;

    @Test
    public void simpleTest() {
        System.out.println("aaa");
    }

    @Test
    public void topic() throws SubCommandException {
        rocketMQTemplate.convertAndSend("hhh","world");
        rocketMQTemplate.send("hhh", MessageBuilder.withPayload(new User("001","jan")).build());
    }

    @Test
    public void sendDemoSimpleMessage() {
        rocketMQTemplate.convertAndSend("TopicTest", "这是一条测试消息");
    }

    @Test
    public void fee() {
        System.out.println(feePlatMqService.openAccountMsg("asdasdasd"));
    }
}
