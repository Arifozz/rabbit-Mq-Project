package com.erc.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeoutException;

public class Sender {
	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();

		try
			(Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();
			channel.queueDeclare("Hello", false, false, false, null);


			int i=0;
			int mCount =1;
			while(i<10){

				String message = "Its your turn  " + mCount + "    " + LocalDateTime.now();
				channel.basicPublish("", "Hello", false, null, message.getBytes());
				i++;
				mCount++;
			}

			System.out.println("message has been sent");
		}


	}
}
