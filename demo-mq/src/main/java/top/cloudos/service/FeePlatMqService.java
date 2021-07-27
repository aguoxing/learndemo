package top.cloudos.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author gx
 * @date 2021/7/10 9:40
 **/
public interface FeePlatMqService {
    SendResult openAccountMsg(String msgInfo);
}
