/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import controller.LogHandler;
import controller.Observer;

/**
 *
 * @author samsung
 */
public class LogGetter {

    

    private static final String LOG_NAME = "logs";
    private static Integer count = 0;
    private static LogHandler logHandler;

    public static void go() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        logHandler = new LogHandler();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(LOG_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, LOG_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        count = 0;
        String message = "";
        while (!message.equals("stop")) {
            
            QueueingConsumer.Delivery delivery = null;
            delivery = consumer.nextDelivery();
            message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            if(count == 0){
                //new Observer().run();
                new Thread(new Observer()).start();
            }
            synchronized (count) {
                count++;
            }
            if (!message.equals("stop")) {
                logHandler.add(message);
            }
            //System.out.println(logHandler);
        }
    }
    
    public static Integer getCount() {
        synchronized (count) {
            return count;
        }
    }
    
    public static String getLog() {
        if (logHandler == null) {
            return "empty";
        } else {
            return logHandler.toString();
        }
    }
}
