package space.kkho.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import space.kkho.chatroom.controller.ChatServer;

@SpringBootApplication
public class ChatroomApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ChatroomApplication.class, args);
		new ChatServer(9000).start();
	}

}