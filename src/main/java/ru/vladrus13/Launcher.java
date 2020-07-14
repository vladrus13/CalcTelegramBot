package ru.vladrus13;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.vladrus13.bot.ParserBot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Launcher {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new ParserBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        // so useless!!! heroku pls do something with ports. I don't need it!
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(System.getenv("PORT")))) {
                Socket socket = serverSocket.accept();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
