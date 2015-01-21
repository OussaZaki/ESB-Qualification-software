package controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaki
 */
public class Observer  extends Thread {
    
    static Integer lastCount;
    static final Integer toleranceTime = 2000;
    static boolean over = false;
    private static final String LOG_NAME = "logs";
    
    @Override
    public void run() {
        System.out.println("starting observer");
        lastCount = LogGetter.getCount();
        while (!over) {
            try {
                Thread.sleep(toleranceTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (lastCount == LogGetter.getCount()) {
                    over = true;
                try {
                    emitStop();
                } catch (IOException ex) {
                    Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            lastCount = LogGetter.getCount();
        }
    }
    
    public void emitStop() throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(LOG_NAME, "fanout");

        String message = "stop";

        channel.basicPublish(LOG_NAME, "", null, message.getBytes());

        channel.close();
        connection.close();
    }

}
