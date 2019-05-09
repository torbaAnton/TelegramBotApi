package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ActivityAction implements Action {

    private Bot bot;

    public ActivityAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateActivity(message.getText(), Math.toIntExact(message.getChatId()));
        bot.sendMsgType(message, "Выберите что вас интересует: ");
    }
}
