package bot;

import action.Action;
import action.impl.AgeAction;
import action.impl.SexAction;
import action.impl.TrainingsAction;
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
        actionMap.put("Тренировки", new TrainingsAction(this));
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
        //sexUser = update.getMessage().getText();
//        String command = update.getMessage().getText();
//        executeCommand(command);
//            sendMessage.setText(executeCommand(text));
//            execute(sendMessage);
        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": " + update.getMessage().getText());
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
//            Action action = actionMap.get(message.getText());
//            action.execute(message);
            switch (message.getText()) {
                case "Тренировки":
                    sendMsgProgram(message, "Выберите: ");
                    break;
                case "Питание":
                    sendMsgProgram(message, "Что желаете?");
                    break;
                case "Мужской":
                    new SexAction(this).execute(message);
//                    sendMsgAge(message, "Выберите ваш возраст:");
                    //addSex();
                    break;
                case "Женский":
                    sendMsgAge(message, "Выберите ваш возраст:");
                    // addSex();
                    break;
                case "16-18":
                    new AgeAction(this).execute(message);
                    sendMsgHeight(message, "Выберите ваш рост");
                    break;
                case "18-35":
                    sendMsgHeight(message, "Выберите ваш рост");
                    break;
                case "35+":
                    sendMsgHeight(message, "Выберите ваш рост");
                    break;
                case "менее 150":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "150-160":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "160-170":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "170-180":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "180-190":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "Более 190":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "Менее 40":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "40-45":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "45-50":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "50-55":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "55-60":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "60-65":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "65-70":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "70-75":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "75-80":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "80-90":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "90-100":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "Более 100":
                    sendMsgType(message, "Что желаете?");
                    break;
                case "Снизить вес":
                    sendMsg(message, "Кардио");
                    break;
                case "Поддерживать вес":
                    sendMsg(message, "Так и живи, лол");
                    break;
                case "Набрать вес":
                    sendMsg(message, "Силовые тренировки");
                    break;
                case "/start":
                    addUser();
                    sendMsgSex(message, "Здравствуйте, рад вас видеть. Я помогу вам с вашими тренировками и питанием. Давайте укажем ваши данные. Выбирайте данные ниже. Ваш пол: ");
                    break;
                case "Назад":
                    sendMsgSex(message, "Выберите данные заново: ");
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

    public void sendMsgProgram(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        sendMsgAndSetButton(sendMessage, "Program");
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
            case "Weight":
                setButtonsWeight(sendMessage);
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
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public void setButtonsAge(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("16-18", "18-35", "35+", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public void setButtonsHeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Менее 150", "150-160", "160-170");
        List<KeyboardButton> secondRowButtons = setUpButtons("170-180", "180-190", "Более 190", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons);
    }

    public void setButtonsWeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Менее 40", "40-45", "50-55");
        List<KeyboardButton> secondRowButtons = setUpButtons("55-60", "60-65", "65-70", "70-75");
        List<KeyboardButton> thirdRowButtons = setUpButtons("75-80", "80-90", "90-100", "100+", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons, thirdRowButtons);
    }

    public void setButtonsProgram(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Снизить вес");
        List<KeyboardButton> secondRowButtons = setUpButtons("Поддерживать вес");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Набрать вес");
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
/*
    public void executeCommand(String command) {
        if (command.contains("/remove")) {
            msg.replace("/remove ", "");
            DBManager.remove(command);
        }
        if (command.contains("/change")) {
            command.replace("/change", "");
            DBManager.change("gc", chat_id);
        }
        if (command.contains("/get")) {
            DBManager.getChatID().toString();
        }
    }
*/

    private void addUser() {
        DBManager.addUser(nameUser, Integer.parseInt(chat_id), nameReal);
    }
    // private void addSex() {DBManager.addSex(sexUser); }

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
