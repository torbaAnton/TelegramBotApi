package bot;

import command.Command;
import command.impl.*;
import login.Login;
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
   // private String sexUser = "";
    Login login = new Login();
   // private Map<Command, String> commandMap = new HashMap<>();

    public static void main(String[] args) {
        //Bot bot = new Bot();
        //bot.fillInMap();
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();

        }
    }

   /* private void fillInMap(){
        commandMap.put(new MessageAgeCommand(this), "Выберите ваш возраст:");
        commandMap.put(new MessageCommand(this), "Что желаете?");
        commandMap.put(new MessageHeightCommand(this), "Выберите ваш рост");
        commandMap.put(new MessageProgramCommand(this), "Выберите:");
        commandMap.put(new MessageSexCommand(this), "Выберите ваш пол:");
        commandMap.put(new MessageTypeCommand(this), "Что желаете?");
        commandMap.put(new MessageWeightCommand(this), "Выберите ваш вес:");
    }
*/
    public void sendMsgType(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsType(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgSex(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsSex(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = String.valueOf(update.getMessage().getChatId());
        nameUser = update.getMessage().getFrom().getUserName();
        nameReal = update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName();
        //sexUser = update.getMessage().getText();
//        String command = update.getMessage().getText();
//        executeCommand(command);
//            sendMessage.setText(executeCommand(text));
//            execute(sendMessage);
        System.out.println(update.getMessage().getFrom().getFirstName()+" " +update.getMessage().getFrom().getLastName() + ": " + update.getMessage().getText());
        Message message = update.getMessage();
        String name1 = "Питание";
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "Тренировки":
                    sendMsgProgram(message, "Выберите: ");
                    break;
                case "Питание":
                    if (name1.contains("Питание"))
                        sendMsgProgram(message, "Что желаете?");
                    else
                        sendMsgSex(message, "Выберите ваш пол");
                    break;
                case "Мужской":
                    sendMsgAge(message, "Выберите ваш возраст:");
                    //addSex();
                    break;
                case "Женский":
                    sendMsgAge(message, "Выберите ваш возраст:");
                   // addSex();
                    break;
                case "16-18":
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
                case "более 190":
                    sendMsgWeight(message, "Выберите ваш вес");
                    break;
                case "менее 40":
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
                case "более 100":
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

    public void sendMsgAge(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsAge(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgHeight(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsHeight(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgWeight(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsWeight(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgProgram(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            setButtonsProgram(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = getSendMessage(message, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    public void setButtonsType(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>()
                ;
        List<KeyboardButton> firstRowButtons = setUpButtons("Питание", "Тренировки", "Назад");

        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsSex(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        List<KeyboardButton> firstRowButtons = setUpButtons("Мужской", "Женский");

        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsAge(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        List<KeyboardButton> firstRowButtons = setUpButtons("16-18", "18-35", "35+", "Назад");

        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsHeight(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        List<KeyboardButton> firstRowButtons = setUpButtons("Менее 150", "150-160", "160-170");
        List<KeyboardButton> secondRowButtons = setUpButtons("170-180", "180-190", "Более 190", "Назад");

        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);
        KeyboardRow keyboardSecondRow = getKeyBoardRow(secondRowButtons);

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsWeight(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        List<KeyboardButton> firstRowButtons = setUpButtons("Менее 40", "40-45", "50-55");
        List<KeyboardButton> secondRowButtons = setUpButtons("55-60", "60-65", "65-70", "70-75");
        List<KeyboardButton> thirdRowButtons = setUpButtons("75-80", "80-90", "90-100", "100+", "Назад");

        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);
        KeyboardRow keyboardSecondRow = getKeyBoardRow(secondRowButtons);
        KeyboardRow keyboardThirdRow = getKeyBoardRow(thirdRowButtons);

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsProgram(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        List<KeyboardButton> firstRowButtons = setUpButtons("Снизить вес");
        List<KeyboardButton> secondRowButtons = setUpButtons("Поддерживать вес");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Набрать вес");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Назад");
        KeyboardRow keyboardFirstRow = getKeyBoardRow(firstRowButtons);
        KeyboardRow keyboardSecondRow = getKeyBoardRow(secondRowButtons);
        KeyboardRow keyboardThirdRow = getKeyBoardRow(thirdRowButtons);
        KeyboardRow keyboardFourthRow = getKeyBoardRow(fourthRowButtons);
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

/*
    public void executeCommand(String command) {
        if (command.contains("/remove")) {
            msg.replace("/remove ", "");
            login.remove(command);
        }
        if (command.contains("/change")) {
            command.replace("/change", "");
            login.change("gc", chat_id);
        }
        if (command.contains("/get")) {
            login.getChatID().toString();
        }
    }
*/

    private void addUser() {
        login.addUser(nameUser, chat_id, nameReal);
    }
   // private void addSex() {login.addSex(sexUser); }

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

    private KeyboardRow getKeyBoardRow(List<KeyboardButton> buttons){
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(buttons);
        return keyboardRow;
    }

    private List<KeyboardButton> setUpButtons(String ... names){
        List<KeyboardButton> buttons = new ArrayList<>();
        for (String name : names){
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
