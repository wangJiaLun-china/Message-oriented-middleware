package com.wjl.mq.base;

/**
 * @author wangJiaLun
 * @date 2019-10-21
 **/
public class ProduceClient {
    public static void main(String[] args) throws Exception {
        MqClient.produce("Hello World");
    }
}
