package com.gdp.rabbitmq;

import com.gdp.rabbitmq.objects.Executable;
import com.gdp.rabbitmq.objects.Task;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 *
 * @author zerosign
 */
public abstract class Publisher extends Executable {

    protected Channel channel;
    protected String queueName;
    protected boolean persistence;

    public Publisher() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void prepare() throws IOException {
        channel.queueDeclare(queueName, true, false, false, null);
        channel.basicQos(1);
    }

    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }

    public void publish(Task task) {
        try {
            ByteArrayOutputStream streamOutput = new ByteArrayOutputStream();
            ObjectOutput writer = new ObjectOutputStream(streamOutput);
            writer.writeObject(task);
            if (isPersistence())
                channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, 
                        streamOutput.toByteArray());
            else 
                channel.basicPublish("", queueName, null, streamOutput.toByteArray());
            writer.close();
            streamOutput.close();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void drop() throws IOException {
        channel.queuePurge(queueName);
    }

}
