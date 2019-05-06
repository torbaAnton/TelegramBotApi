package action.impl;

import action.Action;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TrainingsAction implements Action {
    private Bot bot;

    public TrainingsAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        bot.sendMsgProgram(message, "Выберите: ");
    }
}
