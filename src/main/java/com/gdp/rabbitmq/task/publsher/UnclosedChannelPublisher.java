package com.gdp.rabbitmq.task.publsher;

import com.gdp.rabbitmq.Publisher;
import com.gdp.rabbitmq.utility.Utility;
import com.rabbitmq.client.Channel;

/**
 *
 * @author zerosign
 */
public class UnclosedChannelPublisher extends Publisher {

    public UnclosedChannelPublisher(final Channel channel) {
        this.channel = channel;
        persistence = true;
    }

    @Override
    protected void execute() {
        publish(Utility.GenerateRandomTask());
    }

}
