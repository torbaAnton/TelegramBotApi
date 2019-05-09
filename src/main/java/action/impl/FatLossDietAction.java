package action.impl;

import action.Action;
import add.FormulaForCalories;
import bot.Bot;
import enteties.Diet;
import enteties.User;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FatLossDietAction implements Action {
    private Bot bot;

    public FatLossDietAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(Math.toIntExact(message.getChatId()));
        String age = user.getAge();
        String sex = user.getSex();
        String height = user.getHeight();
        String weight = user.getWeight();
        String activity = user.getActivity();
        Diet diet = dbManager.getDietByAgeAndSexAndHeightAndWeightAndActivity(age, sex, height, weight, activity);
//        String dietFatLoss  = diet.getDietFatLoss();
        double calories = FormulaForCalories.calculateCalorie(message);
        bot.sendMsg(message, (calories - 500) + " калорий");
    }
}
