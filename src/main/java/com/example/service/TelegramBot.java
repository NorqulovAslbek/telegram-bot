package com.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "6517497852:AAF0Yl1ISompZm4SqdCEPAnQUAzBkdHhi6w";
    }

    @Override
    public String getBotUsername() {
        return "javacssbot";
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChat().getId();
            switch (messageText) {
                case "/start" -> {
                    try {
                        sendCommandReceived(chatId, update.getMessage());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> {
                    try {
                        sendMessage(chatId, "Mavjud bo'lmagan komadani kiritingiz!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    private void sendCommandReceived(long chatId, Message message) throws TelegramApiException {

        String text = "Salom " + message.getChat().getFirstName() + " botimizga hush kelibsiz!";
        sendMessage(chatId, text);
    }


    private void sendMessage(long chatId, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        execute(sendMessage);

    }


}
