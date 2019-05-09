package action.impl;

import action.Action;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpAction implements Action {
    private Bot bot;

    public HelpAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        bot.sendMsg(message, "1. Кнопка /start позволяет заново активировать бота\n"+"2. Кнопка 'Назад' всегда возвращает в начало\n"+"3. Тип тренировки подбирается на основе пола и возраста\n"+"4. Тип диеты подбирается на основе:" +
                " пола, возраста, роста, веса и показателя активности");
    }
}
