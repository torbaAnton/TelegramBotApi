package action.impl;

import action.Action;
import bot.Bot;
import enteties.Program;
import enteties.User;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class BulkAction implements Action {
    private Bot bot;

    public BulkAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(Math.toIntExact(message.getChatId()));
        String age = user.getAge();
        String sex = user.getSex();
        Program program = dbManager.getProgramByAgeAndSex(age, sex);
        String bulk = program.getBulk();
        bot.sendMsg(message, bulk);
    }
}
