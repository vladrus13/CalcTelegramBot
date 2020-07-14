package ru.vladrus13.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vladrus13.parser.Expression;
import ru.vladrus13.parser.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

public class ParserBot extends TelegramLongPollingBot {
    String token = "";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Expression expression = new Parser().parse(text);
            SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
            if (!(expression instanceof Error)) {
                sendMessage.setText(String.valueOf(expression.evaluate(Collections.emptyMap())));
            } else {
                sendMessage.setText("Something going wrong!");
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "CalcParseBot";
    }

    @Override
    public String getBotToken() {
        if (token.isEmpty()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("properties.properties"));
                token = properties.getProperty("BOT_TOKEN");
            } catch (IOException exception) {
                System.err.println("Can't load properties, trying get another method");
                token = System.getenv("BOT_TOKEN");
                if (token == null || Objects.equals(token, "")) {
                    System.err.println("Can't find \"BOT_TOKEN\" variable in Environment Variables, throwing exception");
                    exception.printStackTrace();
                }
            }
        }
        return token;
    }
}
