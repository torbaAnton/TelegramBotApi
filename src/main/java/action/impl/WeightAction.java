package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class WeightAction implements Action {
    private Bot bot;

    public WeightAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.updateWeight(message.getText(), Math.toIntExact(message.getChatId()));
        bot.sendMsgActivity(message, "Выберите свой уровень активности:\n"+"(Клавиатуру можно прокручивать вниз)\n"+"Минимальный - никаких физических нагрузок\n"+
                "Низкий - физические нагрузки 1-3 раза в неделю\n"+"Средний - 3-5 дней в неделю\n"+"Высокий - 6-7 раз в неделю\n"+"Очень высокий - тренировки чаще, чем раз в день\n");
    }
}
