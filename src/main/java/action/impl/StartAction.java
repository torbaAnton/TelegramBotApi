package action.impl;

import action.Action;
import bot.Bot;
import login.DBManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartAction implements Action {
    private Bot bot;

    public StartAction(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Message message) {
        DBManager dbManager = DBManager.getInstance();
        String chat_id = String.valueOf(message.getChatId());
        String nameUser = message.getFrom().getUserName();
        String nameReal = message.getFrom().getFirstName() + " " + message.getFrom().getLastName();
        dbManager.addUser(nameUser, Integer.parseInt(chat_id), nameReal);
        bot.sendMsgSex(message, "Здравствуйте, я помогу вам с вашими тренировками и питанием, но прежде, мне нужно кое-что узнать. Выберите свой пол на клавиатуре ниже:\n" +
                "кнопка 'Функции' поможет вам узнать как работает наш бот.");
    }
}
