package com.example.service;

import com.example.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return getBotUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();


            switch (message.getText()) {
                case "/start":
                    try {
                        startCommandReceived(chatId, message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    try {
                        sendMessage(chatId, "Kechirasiz siz buyuruqni hato kiritingiz!!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }


            }
        }
    }

    private void startCommandReceived(Long chatId, Message message) throws TelegramApiException {

        String answer = "Salom " + message.getChat().getFirstName() + " hush kelibsiz";
        sendMessage(chatId, answer);
    }


    private void sendMessage(Long chatId, String sendToMessage) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(sendToMessage);
        execute(sendMessage);
    }


}
