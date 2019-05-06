package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AgeAction implements Action {
    private Bot bot;

    public AgeAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateAge(message.getText(), Math.toIntExact(message.getChatId()));
        bot.sendMsgHeight(message, "Выберите ваш рост: ");
    }
}
