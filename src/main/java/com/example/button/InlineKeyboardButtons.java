package com.example.button;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class InlineKeyboardButtons {


    public static SendMessage getInlineKeyBoardButton(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> list = new LinkedList<>();

        List<InlineKeyboardButton> row1 = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("telegram");
        button1.setUrl("https://t.me/asl_bec");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Instagram");
        button2.setUrl("https://www.instagram.com/asl_bec/");


        row1.add(button1);
        row1.add(button2);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Github");
        button3.setUrl("https://github.com/NorqulovAslbek");
        row2.add(button3);


        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Resume");
        button4.setCallbackData("send_resume");
        row2.add(button4);

        list.add(row1);
        list.add(row2);


        inlineKeyboardMarkup.setKeyboard(list);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;

    }

}
