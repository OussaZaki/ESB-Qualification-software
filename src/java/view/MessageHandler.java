package view;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Scenario;
import providers.Exception_Exception;

/**
 *
 * @author Oussama
 */
public class MessageHandler {

    private static final String EXCHANGE_NAME = "configuration";
    private static Boolean key;
    public static String sendConfig(String who, String config) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        
        String forWho = who;
        String message = config;
        channel.basicPublish(EXCHANGE_NAME, who, null, message.getBytes());
        
        
        channel.close();
        connection.close();
        return " [x] Sent '" + forWho + "':'" + message + "'";
    }

    public static void providerHandler(final Scenario scenario) {
        
        for (int i = 1; i <= scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + i) {
                @Override
                public void run() {
                    try {
                        int i = Integer.parseInt(getName());
                        if(i == 1)
                            key = true;
                        System.out.println("provider " + i + " is ready");
                        configureProvider(Integer.parseInt(getName()));
                        System.out.println("provider " + i + " is done");
                    } catch (Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            new Thread("" + (i + scenario.getNumberConsumerProvider())) {
                @Override
                public void run() {
                    try {
                        int i = Integer.parseInt(getName()) - scenario.getNumberConsumerProvider();
                        sleep(3000);
                        String config = "" + scenario.getProviders().get(i - 1).getDataExchangeSize().getContent() + ";"
                                + scenario.getProviders().get(i - 1).getProcessingTime().getContent();
                        System.out.println("provider config: " + config);
                        System.out.println(sendConfig("provider" + (i), config));
                        if(i == scenario.getNumberConsumerProvider())
                            key = false;
                    } catch (Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
       
    }

    public static void consumerHandler(final Scenario scenario) {
        while(key){
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 1; i <= scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + i) {
                @Override
                public void run() {
                    try {
                        System.out.println("consumer " + getName() + " is ready");
                        configureConsumer(Integer.parseInt(getName()));
                        System.out.println("consumer " + getName() + " is done");
                    } catch (consumers.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            new Thread("" + (i + 5)) {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        String config = "" + scenario.getConsumers().get(Integer.parseInt(getName()) - 6).getRequestBySeconde();
                        System.out.println("consumer config: " + config);
                        System.out.println(sendConfig("consumer" + (Integer.parseInt(getName()) - 5), config));
                    } catch (Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        
    }

    private static String configureConsumer(int id) throws consumers.Exception_Exception {
        String message = "";
        switch (id) {
            case 1:
                message = configureC1();
                break;
            case 2:
                message = configureC2();
                break;
            case 3:
                message = configureC3();
                break;
            case 4:
                message = configureC4();
                break;
            case 5:
                message = configureC5();
                break;
        }
        return message;
    }

    private static String configureProvider(int id) throws providers.Exception_Exception {
        String message = "";
        switch (id) {
            case 1:
                message = configureP1();
                break;
            case 2:
                message = configureP2();
                break;
            case 3:
                message = configureP3();
                break;
            case 4:
                message = configureP4();
                break;
            case 5:
                message = configureP5();
                break;
        }
        return message;
    }

    private static String configureC1() throws consumers.Exception_Exception {
        consumers.Consumer1_Service service = new consumers.Consumer1_Service();
        consumers.Consumer1 port = service.getConsumer1Port();
        return port.test();
    }

    private static String configureC2() throws consumers.Exception_Exception {
        consumers.Consumer2_Service service = new consumers.Consumer2_Service();
        consumers.Consumer2 port = service.getConsumer2Port();
        return port.test();
    }

    private static String configureC3() throws consumers.Exception_Exception {
        consumers.Consumer3_Service service = new consumers.Consumer3_Service();
        consumers.Consumer3 port = service.getConsumer3Port();
        return port.test();
    }

    private static String configureC4() throws consumers.Exception_Exception {
        consumers.Consumer4_Service service = new consumers.Consumer4_Service();
        consumers.Consumer4 port = service.getConsumer4Port();
        return port.test();
    }

    private static String configureC5() throws consumers.Exception_Exception {
        consumers.Consumer5_Service service = new consumers.Consumer5_Service();
        consumers.Consumer5 port = service.getConsumer5Port();
        return port.test();
    }

    private static String configureP1() throws providers.Exception_Exception {
        providers.Provider1_Service service = new providers.Provider1_Service();
        providers.Provider1 port = service.getProvider1Port();
        return port.test();
    }

    private static String configureP2() throws providers.Exception_Exception {
        providers.Provider2_Service service = new providers.Provider2_Service();
        providers.Provider2 port = service.getProvider2Port();
        return port.test();
    }

    private static String configureP3() throws providers.Exception_Exception {
        providers.Provider3_Service service = new providers.Provider3_Service();
        providers.Provider3 port = service.getProvider3Port();
        return port.test();
    }

    private static String configureP4() throws providers.Exception_Exception {
        providers.Provider4_Service service = new providers.Provider4_Service();
        providers.Provider4 port = service.getProvider4Port();
        return port.test();
    }

    private static String configureP5() throws providers.Exception_Exception {
        providers.Provider5_Service service = new providers.Provider5_Service();
        providers.Provider5 port = service.getProvider5Port();
        return port.test();
    }

}
