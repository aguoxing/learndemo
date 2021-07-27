package top.cloudos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author gx
 * @date 2021/7/10 9:38
 **/
@Service
public class ParamConfigService {
    @Value("${fee-plat.fee-plat-group}")
    public String feePlatGroup ;
    @Value("${fee-plat.fee-plat-topic}")
    public String feePlatTopic ;
    @Value("${fee-plat.fee-account-tag}")
    public String feeAccountTag ;
}