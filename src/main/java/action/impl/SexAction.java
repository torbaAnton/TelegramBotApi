package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SexAction implements Action {
    private Bot bot;

    public SexAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateSex(message.getText(), Math.toIntExact(message.getChatId()));
        bot.sendMsgAge(message, "Выберите ваш возраст: ");
    }
}
