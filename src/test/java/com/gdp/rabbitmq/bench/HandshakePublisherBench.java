/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.publsher.ext.HandshakePublisher;
import java.io.IOException;
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
public class HandshakePublisherBench {
    
    public HandshakePublisherBench() {
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
    public void testBenchHandshakePublisher() throws IOException {
        HandshakePublisher publisher;
        publisher = new HandshakePublisher();
        publisher.setQueueName("HANDSHAKE");
        publisher.prepare();
         for (long ii = 0; ii < 1000000000L; ii++) {
            publisher.run();
        }
        publisher.drop();
        
    }
}
