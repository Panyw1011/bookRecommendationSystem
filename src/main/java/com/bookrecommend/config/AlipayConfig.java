package com.bookrecommend.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig implements ApplicationRunner {

    // 应用id
    @Value("${alipay.appId}")
    private String appId;

    // 私钥
    @Value("${alipay.privateKey}")
    private String privateKey;

    // 公钥
    @Value("${alipay.publicKey}")
    private String publicKey;

    // 支付宝网关
    @Value("${alipay.gateway}")
    private String gateway;

    // 支付成功后的接口回调地址
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    /**
     *  项目初始化事件
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //初始化支付宝SDK
        Factory.setOptions(getOptions());
        System.out.println("**********支付宝SDK初始化完成**********");
    }

    private Config getOptions() {

        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = this.gateway;
        config.signType = "RSA2";

        config.appId = this.appId;

        config.merchantPrivateKey = this.privateKey;

        config.alipayPublicKey = this.publicKey;

        config.notifyUrl = notifyUrl;

        return config;
    }
}
