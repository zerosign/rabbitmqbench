package com.gdp.rabbitmq.task.publsher;

import com.gdp.rabbitmq.Publisher;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import java.io.IOException;

/**
 *
 * @author zerosign
 */
public class SharedConnectionPublisher extends Publisher {
    
    private Connection connection;
    
    public SharedConnectionPublisher(Connection connection) throws IOException {
        this.connection = connection;
        channel = connection.createChannel();
        persistence = true;
    }

    @Override
    protected void execute() {
        publish(Utility.GenerateRandomTask());
        
        try {
            channel.close();
            channel = connection.createChannel();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
