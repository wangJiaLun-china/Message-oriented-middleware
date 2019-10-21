package com.wjl.mq.base;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息处理中心
 *
 * @author wangJiaLun
 * @date 2019-10-13
 **/
public class Broker {

    /**
     *  队列存储消息的最大数量
     */
    private final static int MAX_SIZE = 4;

    /**
     *  保存消息数据的容器
     *   ArrayBlockingQueue 实现 BlockingQueue
     *   BlockingQueue 提供
     *      三个添加元素方法
     *          add：添加元素到队列里，添加成功返回true，由于容量满了添加失败会抛出IllegalStateException异常
     *          offer：添加元素到队列里，添加成功返回true，添加失败返回false
     *          put：添加元素到队列里，如果容量满了会阻塞直到容量不满
     *      三个删除元素方法
     *          poll：删除队列头部元素，如果队列为空，返回null。否则返回元素
     *          remove：基于对象找到对应的元素，并删除。删除成功返回true，否则返回false
     *          take：删除队列头部元素，如果队列为空，一直阻塞到队列有元素并删除
     */
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    /**
     *  生产消息
     * @param msg 消息体
     */
    public static void produce(String msg){
        if (messageQueue.offer(msg)) {
            System.out.println("成功向消息处理中心投递消息: "+ msg + ", 当前暂存消息数量为: "+ messageQueue.size());
        }else {
            System.out.println("消息处理中心暂存消息，达到最大负荷");
        }
        System.out.println("============================");
    }

    public static String consume(){
        String msg = messageQueue.poll();
        if (msg != null) {
            // 消费条件满足， 从消息容器中取出一条信息
            System.out.println("已经消费信息: "+msg + ", 当前暂存消息数量是: " + messageQueue.size());
        }else {
            System.out.println("消息处理中心内无消息可供消费");
        }
        System.out.println("============================");
        return msg;
    }

}
