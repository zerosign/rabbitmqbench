package com.gdp.rabbitmq.task.consumer;

import com.gdp.rabbitmq.Consumer;
import com.gdp.rabbitmq.objects.Task;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import java.io.IOException;

/**
 *
 * @author zerosign
 */
public class SharedConnectionConsumer extends Consumer {

    private final Connection connection;
    
    public SharedConnectionConsumer(final Connection connection) throws IOException {
        this.connection = connection;
        channel = connection.createChannel();
    }
    
    @Override
    protected void execute() {
         try {
            final Task task = consume(Utility.GenerateRandomTaskType());
            
        } catch (IOException | InterruptedException | ClassNotFoundException ex) {
            
        }
    }
    
}
