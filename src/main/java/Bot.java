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
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private String chat_id;
    private String nameUser = "";
    Login login = new Login();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();

        }
    }

    public void sendMsgType(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsType(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgSex(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsSex(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgAge(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsAge(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgHeight(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsHeight(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgWeight(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsWeight(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgProgram(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtonsProgram(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
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
        String text = update.getMessage().getText();
        try{
            sendMessage.setText(getMsg(text));
            execute(sendMessage);
            System.out.println(update.getMessage().getFrom().getUserName() + ": " + update.getMessage().getText());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
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
                    break;
                case "Женский":
                    sendMsgAge(message, "Выберите ваш возраст:");
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
                    sendMsgSex(message, "Здравствуйте, рад вас видеть. Я помогу вам с вашими тренировками и питанием. Давайте укажем ваши данные. Выбирайте данные ниже. Ваш пол: ");
                    break;
                case "Назад":
                    sendMsgSex(message,"Выберите данные заново: ");
                default:

            }
        }
    }


    public void setButtonsType(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Питание"));
        keyboardFirstRow.add(new KeyboardButton("Тренировки"));
        keyboardFirstRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsSex(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Мужской"));
        keyboardFirstRow.add(new KeyboardButton("Женский"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsAge(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("16-18"));
        keyboardFirstRow.add(new KeyboardButton("18-35"));
        keyboardFirstRow.add(new KeyboardButton("35+"));
        keyboardFirstRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsHeight(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("менее 150"));
        keyboardFirstRow.add(new KeyboardButton("150-160"));
        keyboardFirstRow.add(new KeyboardButton("160-170"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("170-180"));
        keyboardSecondRow.add(new KeyboardButton("180-190"));
        keyboardSecondRow.add(new KeyboardButton("более 190"));
        keyboardSecondRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsWeight(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("менее 40"));
        keyboardFirstRow.add(new KeyboardButton("40-45"));
        keyboardFirstRow.add(new KeyboardButton("45-50"));
        keyboardFirstRow.add(new KeyboardButton("50-55"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("55-60"));
        keyboardSecondRow.add(new KeyboardButton("60-65"));
        keyboardSecondRow.add(new KeyboardButton("65-70"));
        keyboardSecondRow.add(new KeyboardButton("70-75"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("75-80"));
        keyboardThirdRow.add(new KeyboardButton("80-90"));
        keyboardThirdRow.add(new KeyboardButton("90-100"));
        keyboardThirdRow.add(new KeyboardButton("более 100"));
        keyboardThirdRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsProgram(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Снизить вес"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Поддерживать вес"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("Набрать вес"));
        KeyboardRow keyboardFourthRow = new KeyboardRow();
        keyboardFourthRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getMsg(String msg){
        if (msg.contains("/remove")){
            msg.replace("/remove ","");
            login.remove(msg);
        }
        if (msg.contains("/add")){
            login.add(nameUser, chat_id);
        }
if (msg.contains("/change")){
    msg.replace("/change","");
    login.change("gc", chat_id);
}
if(msg.contains("/get")){
    return login.getChatID().toString();
}
return "OK";
    }
    public String getBotUsername() {
        return "CoachBot";
    }

    public String getBotToken() {
        return "752974627:AAGhyGf2FYZsMitGJs22OSWjwhDL-ThqOWU";
    }
}
