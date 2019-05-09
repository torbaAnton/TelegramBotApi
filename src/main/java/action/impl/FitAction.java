package action.impl;

import action.Action;
import bot.Bot;
import enteties.Program;
import enteties.User;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FitAction implements Action {
    private Bot bot;

    public FitAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(Math.toIntExact(message.getChatId()));
        String age = user.getAge();
        String sex = user.getSex();
        Program program = dbManager.getProgramByAgeAndSex(age, sex);
        String fit = program.getFit();
        bot.sendMsg(message, fit);
    }
}

