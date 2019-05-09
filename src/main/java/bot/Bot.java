package bot;

import action.Action;
import action.impl.*;
import login.DBManager;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private String chat_id;
    private String nameUser = "";
    private String nameReal = "";
    DBManager DBManager = new DBManager();
    private Map<String, Action> actionMap;

    {
        actionMap = new HashMap<>();
        actionMap.put("/start", new StartAction(this));
        actionMap.put("Мужской", new SexAction(this));
        actionMap.put("Женский", new SexAction(this));
        actionMap.put("16-18", new AgeAction(this));
        actionMap.put("18-35", new AgeAction(this));
        actionMap.put("35+", new AgeAction(this));
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
        actionMap.put("Более 100", new WeightAction(this));
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
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = String.valueOf(update.getMessage().getChatId());
        nameUser = update.getMessage().getFrom().getUserName();
        nameReal = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": " + update.getMessage().getText());
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
           Action action = actionMap.get(message.getText());
           action.execute(message);

            switch (message.getText()) {
                case "/start":
                    addUser();
                    break;
                default:

            }

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


    private void sendMsgAndSetButton(SendMessage sendMessage, String buttonType){
        switch (buttonType){
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

    public void setButtonsType(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Питание", "Тренировки", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public void setButtonsSex(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Мужской", "Женский");
        List<KeyboardButton> secondRowButtons = setUpButtons("Функции");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons);
    }

    public void setButtonsAge(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("16-18", "18-35", "35+", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public void setButtonsHeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("150-160", "160-170");
        List<KeyboardButton> secondRowButtons = setUpButtons("170-180", "180-190", "190-200", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons);
    }

    public void setButtonsWeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("40-45", "50-55");
        List<KeyboardButton> secondRowButtons = setUpButtons("55-60", "60-65", "65-70", "70-75");
        List<KeyboardButton> thirdRowButtons = setUpButtons("75-80", "80-90", "90-100", "100+", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons, thirdRowButtons);
    }
    public void setButtonsActivity(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Минимальный");
        List<KeyboardButton> secondRowButtons = setUpButtons("Низкий");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Средний");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Высокий");
        List<KeyboardButton> fifthRowButtons = setUpButtons("Очень высокий");
        List<KeyboardButton> sixthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons, fifthRowButtons, sixthRowButtons);
    }

    public void setButtonsProgram(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Снизить вес");
        List<KeyboardButton> secondRowButtons = setUpButtons("Поддерживать вес");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Набрать вес");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, true, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons);
    }
    public void setButtonsDiet(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Диета для снижения веса");
        List<KeyboardButton> secondRowButtons = setUpButtons("Диета для поддержания веса");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Диета для набора веса");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, true, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons);
    }


    private void setUpKeyboard(SendMessage sendMessage, boolean oneTimeKeyboard, List<KeyboardButton>... rowButtons){
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, oneTimeKeyboard);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        for (List<KeyboardButton> list : rowButtons){
            KeyboardRow keyBoardRow = getKeyBoardRow(list);
            keyboardRowList.add(keyBoardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
    private void addUser() {
        DBManager.addUser(nameUser, Integer.parseInt(chat_id), nameReal);
    }

    public String getBotUsername() {
        return "CoachBot";
    }

    public String getBotToken() {
        return "752974627:AAGhyGf2FYZsMitGJs22OSWjwhDL-ThqOWU";
    }

    private void setUpKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup, boolean b) {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(b);
    }

    private KeyboardRow getKeyBoardRow(List<KeyboardButton> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(buttons);
        return keyboardRow;
    }

    private List<KeyboardButton> setUpButtons(String... names) {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (String name : names) {
            buttons.add(new KeyboardButton(name));
        }
        return buttons;
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(SendMessage sendMessage, boolean b) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        setUpKeyboard(replyKeyboardMarkup, b);
        return replyKeyboardMarkup;
    }

    public SendMessage getSendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        return sendMessage;
    }
    }
