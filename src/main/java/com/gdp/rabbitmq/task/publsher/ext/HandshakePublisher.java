/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gdp.rabbitmq.task.publsher.ext;

import com.gdp.rabbitmq.Publisher;
import com.gdp.rabbitmq.task.publsher.CompleteRecycledPublisher;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zerosign
 */
public class HandshakePublisher extends Publisher {
    private Connection connection;

    public HandshakePublisher() throws IOException {ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        connection = factory.newConnection();
        channel = connection.createChannel();

    }

    @Override
    protected void execute() {
        try {
            channel.confirmSelect();
            publish(Utility.GenerateRandomTask());
            channel.waitForConfirmsOrDie();
            channel.close();
            connection.close();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(HandshakePublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
