package com.example.gpt;

import com.example.service.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class GptApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(GptApplication.class, args);
		TelegramBot telegramBot=new TelegramBot();
		TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(telegramBot);
	}

}
