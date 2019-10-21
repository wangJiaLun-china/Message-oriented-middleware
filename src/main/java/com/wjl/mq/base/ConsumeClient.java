package com.wjl.mq.base;

/**
 * @author wangJiaLun
 * @date 2019-10-21
 **/
public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        String message = MqClient.consume();
        System.out.println(message);
    }
}
