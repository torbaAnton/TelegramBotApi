package bot;

import action.Action;
import action.impl.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.HashMap;
import java.util.Map;

import static util.ButtonsAnsKeyboarUtil.*;

public class Bot extends TelegramLongPollingBot {
    private Map<String, Action> actionMap;

    {
        actionMap = new HashMap<>();
        actionMap.put("/start", new StartAction(this));
        actionMap.put("Мужской", new SexAction(this));
        actionMap.put("Женский", new SexAction(this));
        actionMap.put("16-18", new AgeAction(this));
        actionMap.put("18-35", new AgeAction(this));
        actionMap.put("35-50", new AgeAction(this));
        actionMap.put("150-160", new HeightAction(this));
        actionMap.put("160-170", new HeightAction(this));
        actionMap.put("170-180", new HeightAction(this));
        actionMap.put("180-190", new HeightAction(this));
        actionMap.put("190-200", new HeightAction(this));
        actionMap.put("40-45", new WeightAction(this));
        actionMap.put("45-50", new WeightAction(this));
        actionMap.put("50-55", new WeightAction(this));
        actionMap.put("55-60", new WeightAction(this));
        actionMap.put("60-65", new WeightAction(this));
        actionMap.put("65-70", new WeightAction(this));
        actionMap.put("70-75", new WeightAction(this));
        actionMap.put("75-80", new WeightAction(this));
        actionMap.put("80-90", new WeightAction(this));
        actionMap.put("90-100", new WeightAction(this));
        actionMap.put("100-110", new WeightAction(this));
        actionMap.put("Тренировки", new TrainingsAction(this));
        actionMap.put("Питание", new FoodAction(this));
        actionMap.put("Поддерживать вес", new FitAction(this));
        actionMap.put("Набрать вес", new BulkAction(this));
        actionMap.put("Снизить вес", new FatLossAction(this));
        actionMap.put("Назад", new BackAction(this));
        actionMap.put("Диета для снижения веса", new FatLossDietAction(this));
        actionMap.put("Диета для поддержания веса", new FitDietAction(this));
        actionMap.put("Диета для набора веса", new BulkDietAction(this));
        actionMap.put("Минимальный", new ActivityAction(this));
        actionMap.put("Низкий", new ActivityAction(this));
        actionMap.put("Средний", new ActivityAction(this));
        actionMap.put("Высокий", new ActivityAction(this));
        actionMap.put("Очень высокий", new ActivityAction(this));
        actionMap.put("Функции", new HelpAction(this));
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();

        }
    }

    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": " + update.getMessage().getText());
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            Action action = actionMap.get(message.getText());
            action.execute(message);
        }
    }

    public void sendMsgAndSetButton(SendMessage sendMessage, String buttonType) {
        switch (buttonType) {
            case "Type":
                setButtonsType(sendMessage);
                break;
            case "Sex":
                setButtonsSex(sendMessage);
                break;
            case "Age":
                setButtonsAge(sendMessage);
                break;
            case "Height":
                setButtonsHeight(sendMessage);
                break;
            case "Program":
                setButtonsProgram(sendMessage);
                break;
            case "Diet":
                setButtonsDiet(sendMessage);
                break;
            case "Weight":
                setButtonsWeight(sendMessage);
                break;
            case "Activity":
                setButtonsActivity(sendMessage);
                break;
            default:
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgType(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Type");
    }

    public void sendMsgSex(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Sex");
    }

    public void sendMsgAge(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Age");
    }

    public void sendMsgHeight(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Height");
    }

    public void sendMsgWeight(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Weight");
    }

    public void sendMsgActivity(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Activity");
    }

    public void sendMsgProgram(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Program");
    }

    public void sendMsgDiet(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Diet");
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Msg");
    }

    public String getBotUsername() {
        return "CoachBot";
    }

    public String getBotToken() {
        return "";
    }
}
