package top.cloudos.service.impl;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import top.cloudos.service.FeePlatMqService;

import javax.annotation.Resource;

/**
 * @author gx
 * @date 2021/7/10 9:39
 **/
@Service
public class FeePlatMqServiceImpl implements FeePlatMqService {
    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService;

    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.feePlatGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.feePlatTopic,
                    paramConfigService.feeAccountTag,
                    "fee_open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}