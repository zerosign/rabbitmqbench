package com.gdp.rabbitmq.task.consumer.ext;

import com.gdp.rabbitmq.Consumer;
import com.gdp.rabbitmq.objects.Task;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zerosign
 */
public class ManualAckConsumer extends Consumer {

    public ManualAckConsumer() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
    }
    
    @Override
    protected void execute() {
        try {
            final Task task = consume(Utility.GenerateRandomTaskType());
        } catch (IOException ex) {
            Logger.getLogger(ManualAckConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManualAckConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManualAckConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
