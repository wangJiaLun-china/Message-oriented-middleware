package com.wjl.mq.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 消息处理客户端
 *
 * @author wangJiaLun
 * @date 2019-10-13
 **/
public class MqClient {

    /**
     *  生产消息
     */
    public static void produce(String message) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(message);
        out.flush();
    }

    /**
     *  消费消息
     */
    public static String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        // 先向消息队列发送字符串"CONSUME" 表示消费
        out.println("CONSUME");
        out.flush();
        // 再向消息队列获取一条消息
        return in.readLine();
    }
}
