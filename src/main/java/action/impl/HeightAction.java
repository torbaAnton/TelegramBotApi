package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HeightAction implements Action {
    private Bot bot;

    public HeightAction(Bot bot) { this.bot = bot; }
    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateHeight(message.getText(), Math.toIntExact(message.getChatId()));
        bot.sendMsgWeight(message, "Выберите ваш вес или ближайшее к нему значение: ");
    }
}
