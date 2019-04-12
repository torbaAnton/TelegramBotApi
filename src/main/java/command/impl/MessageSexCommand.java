package command.impl;

import bot.Bot;
import command.Command;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageSexCommand implements Command {

    private Bot bot;

    public MessageSexCommand(Bot bot){
        this.bot = bot;
    }

    @Override
    public void sendMessage(Message message, String text) {

    }
}
