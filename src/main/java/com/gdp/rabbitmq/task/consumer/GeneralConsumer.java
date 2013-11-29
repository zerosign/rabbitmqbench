package com.gdp.rabbitmq.task.consumer;

import com.gdp.rabbitmq.Consumer;
import com.gdp.rabbitmq.objects.Task;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;

/**
 *
 * @author zerosign
 */
public class GeneralConsumer extends Consumer {


    public GeneralConsumer() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
    }

    @Override
    protected void execute() {
        try {
            final Task task = consume(Utility.GenerateRandomTaskType());
            
           
            //channel.close();
            //connection.close();
            //ConnectionFactory factory = new ConnectionFactory();
            //factory.setHost("127.0.0.1");
            //connection = factory.newConnection();
            //channel = connection.createChannel();
        } catch (IOException | InterruptedException | ClassNotFoundException ex) {
            
        }
    }

}
