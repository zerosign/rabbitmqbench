/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.publsher.SharedConnectionPublisher;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author zerosign
 */
public class SharedConnectionPublisherBench {

    private Connection connection;

    public SharedConnectionPublisherBench() {
        try {
            final ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            connection = factory.newConnection();

        } catch (IOException ex) {
            Logger.getLogger(SharedConnectionBench.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testBenchSharedConnectionPublisher() throws IOException {
        final SharedConnectionPublisher publisher
                = new SharedConnectionPublisher(connection);
        publisher.setQueueName("BENCH_SHARED");
        publisher.prepare();

        for (long ii = 0; ii < 1000000000L; ii++) {
            publisher.run();
        }

        publisher.drop();
    }
}
