package com.erc.app;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer3 {
	public static void main(String[] args) throws IOException, TimeoutException {


		ConnectionFactory factory = new ConnectionFactory();

		
		Connection connection = factory.newConnection();

		com.rabbitmq.client.Channel channel = connection.createChannel();

		channel.basicConsume("temperature", false,

				new DefaultConsumer(channel) {
					@Override
					public void handleDelivery(String consumerTag,
											   Envelope envelope,
											   AMQP.BasicProperties properties,
											   byte[] body)
							throws IOException
					{
						long deliveryTag = envelope.getDeliveryTag();
						System.out.println(deliveryTag);
						System.out.println( new String(body));
						// positively acknowledge a single delivery, the message will
						// be discarded

						if(deliveryTag%2==0){
							channel.basicAck(deliveryTag, false);
						}else{

						}


					}
				});

/*
		channel.basicConsume("temperature", false, (consumerTag, message) -> {

			String m = new String(message.getBody(), "UTF-8");
			System.out.println("Consumer2  just received a message = " +m);


		}, consumerTag -> {	});
*/



	}
	
}
