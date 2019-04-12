package command.impl;

import bot.Bot;
import command.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageWeightCommand implements Command {

    private Bot bot;

    public MessageWeightCommand(Bot bot){
        this.bot = bot;
    }

    @Override
    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = bot.getSendMessage(message, text);
        try {
            bot.setButtonsWeight(sendMessage);
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
