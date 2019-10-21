package com.wjl.mq.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 对外Broker服务
 *
 * @author wangJiaLun
 * @date 2019-10-13
 **/
public class BrokerServer implements Runnable {

    /**
     *  服务使用端口号
     */
    public static int SERVER_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true){
                String str = in.readLine();
                if (null == str) {
                    continue;
                }
                System.out.println("接受到原始数据: " + str);
                // CONSUME 表示要消费一条信息
                if ("CONSUME".equals(str)) {
                    // 从消息队列中消费一条信息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                }else {
                    // 其他情况都表示生产消息放到消息队列中
                    Broker.produce(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}
