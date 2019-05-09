package action.impl;

import action.Action;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartAction implements Action {
    private Bot bot;

    public StartAction(Bot bot) {
        this.bot = bot;
    }
    @Override
    public void execute(Message message) {
        bot.sendMsgSex(message, "Здравствуйте, я помогу вам с вашими тренировками и питанием, но прежде, мне нужно кое-что узнать. Выберите свой пол на клавиатуре ниже:\n"+
                "кнопка 'Функции' поможет вам узнать как работает наш бот.");
    }
}
