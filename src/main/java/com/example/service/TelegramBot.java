package com.example.service;

import com.example.button.InlineKeyboardButtons;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final String fileLocation = "C:\\Users\\Aslbek\\Downloads\\gpt\\util\\photo_2024-07-24_15-33-37.jpg";
    private final String resumeLocation = "C:\\Users\\Aslbek\\Downloads\\gpt\\util\\Aslbek Resume (2).pdf";

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
//                  sendCommandReceived(chatId, update.getMessage());
                    SendMessage inlineKeyBoardButton = InlineKeyboardButtons.getInlineKeyBoardButton(chatId);
                    SendPhoto sendPhoto = sendPhotoAndInlineKeyboardMarkup(String.valueOf(chatId), this.fileLocation, inlineKeyBoardButton);
                    try {
                        execute(sendPhoto);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    //                        execute(inlineKeyBoardButton);

                }

                default -> {
                    try {
                        sendMessage(chatId, "Mavjud bo'lmagan komadani kiritingiz!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getFrom().getId();
            if (data.equals("send_resume")){
                sendPdfFile(chatId);
            }
        }

    }


    private SendPhoto sendPhotoAndInlineKeyboardMarkup(String chatId, String filePath, SendMessage sendMessage) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
        sendPhotoRequest.setReplyMarkup(sendMessage.getReplyMarkup());
        sendPhotoRequest.setCaption(" Salom bu yerda Men haqimda malumot ko'rishingiz mumkun. ");
        sendPhotoRequest.setProtectContent(false);

        return sendPhotoRequest;
    }

    private void sendPdfFile(long chatId) {
        SendDocument document = new SendDocument();
        document.setChatId(chatId);
        document.setDocument(new InputFile(new File("C:\\Users\\Aslbek\\Downloads\\gpt\\util\\Aslbek Resume (2).pdf")));

        try {
            execute(document);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
