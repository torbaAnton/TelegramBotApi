package action.impl;

import action.Action;
import add.FormulaForCalories;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FatLossDietAction implements Action {
    private Bot bot;

    public FatLossDietAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        double calories = FormulaForCalories.calculateCalorie(message);
        bot.sendMsg(message, (calories - 500) + " калорий вам нужно потреблять, чтобы худеть на 0,5кг жира в неделю");
    }
}
