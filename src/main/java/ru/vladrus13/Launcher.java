package ru.vladrus13;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.vladrus13.bot.ParserBot;

public class Launcher {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new ParserBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
