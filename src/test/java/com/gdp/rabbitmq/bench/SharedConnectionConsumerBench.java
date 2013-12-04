package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.consumer.SharedConnectionConsumer;
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

/**
 *
 * @author zerosign
 */
public class SharedConnectionConsumerBench {

    private Connection connection;

    public SharedConnectionConsumerBench() {
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

    public void testBenchSharedConnectionConsumer() throws IOException {
        final SharedConnectionConsumer consumer
                = new SharedConnectionConsumer(connection);
        consumer.setAutoAck(false);
        consumer.setQueueName("BENCH_SHARED");
        consumer.prepare();
        for (long ii = 0; ii < 1000000000L; ii++) {
            consumer.run();
        }

    }
}
