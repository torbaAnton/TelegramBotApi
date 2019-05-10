package action.impl;

import action.Action;
import add.FormulaForCalories;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FitDietAction implements Action {
    private Bot bot;

    public FitDietAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        double calories = FormulaForCalories.calculateCalorie(message);
        bot.sendMsg(message, calories + " калорий вам нужно потреблять, чтобы поддерживать форму");
    }
}
