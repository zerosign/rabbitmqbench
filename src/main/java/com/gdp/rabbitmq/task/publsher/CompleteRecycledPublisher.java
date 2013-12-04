package com.gdp.rabbitmq.task.publsher;

import com.gdp.rabbitmq.Publisher;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;

/**
 *
 * @author zerosign
 */
public class CompleteRecycledPublisher extends Publisher {

    private Connection connection;

    public CompleteRecycledPublisher() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        connection = factory.newConnection();

        channel = connection.createChannel();
        persistence = true;
    }

    @Override
    public void execute() {
        try {
            publish(Utility.GenerateRandomTask());
            channel.close();
            connection.close();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException ex) {

            System.err.println(ex.getMessage());
        }

    }

}
