package com.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;



public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi mybot = new TelegramBotsApi();
        try {
            mybot.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }


    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();


        SendMessage answer = new SendMessage();
        answer.setText("Привет, " + message.getFrom().getFirstName() + "! Это телеграм-бот салона красоты \"Мята\". " +
                "С его помощью Вы можете получить необходимую информацию, оставить заявку на обратный звонок от нашего " +
                "администратора или записаться на процедуру! Пожалуйста, выберете необходимое действие");
        answer.setChatId(message.getChatId());
        answer.setReplyMarkup(getMainMenu());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String getBotUsername() {
        return "fzhura_bot";
    }

    public String getBotToken() {
        return "1355598509:AAEkVYRKs_CCrmnRl4Zo3HeYQlnCDXt6Tl0";
    }


    private ReplyKeyboardMarkup getMainMenu() {
        ReplyKeyboardMarkup key = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();


        row1.add("Записаться на процедуру");
        row1.add("Перезвоните мне");

        row2.add("Наш прайс");
        row2.add("Наш адрес");

        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        rows.add(row1);
        rows.add(row2);

        key.setKeyboard(rows);

        return key;
    }
    
}
