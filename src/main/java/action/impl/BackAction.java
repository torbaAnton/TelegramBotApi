package action.impl;

import action.Action;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class BackAction implements Action {
    private Bot bot;

    public BackAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        bot.sendMsgSex(message, "Выберите данные заново: ");
    }
}
