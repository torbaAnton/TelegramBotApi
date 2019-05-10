package util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;

public final class ButtonsAnsKeyboarUtil {
    private ButtonsAnsKeyboarUtil() {}
    public static SendMessage getSendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        return sendMessage;
    }
    private static List<KeyboardButton> setUpButtons(String... names) {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (String name : names) {
            buttons.add(new KeyboardButton(name));
        }
        return buttons;
    }

    private static void setUpKeyboard(SendMessage sendMessage, boolean oneTimeKeyboard, List<KeyboardButton>... rowButtons){
        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage, oneTimeKeyboard);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        for (List<KeyboardButton> list : rowButtons){
            KeyboardRow keyBoardRow = getKeyBoardRow(list);
            keyboardRowList.add(keyBoardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    private static void setUpKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup, boolean b) {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(b);
    }

    private static  KeyboardRow getKeyBoardRow(List<KeyboardButton> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(buttons);
        return keyboardRow;
    }

    private static ReplyKeyboardMarkup getReplyKeyboardMarkup(SendMessage sendMessage, boolean b) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        setUpKeyboard(replyKeyboardMarkup, b);
        return replyKeyboardMarkup;
    }

    public static void setButtonsType(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Питание", "Тренировки", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public static void setButtonsSex(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Мужской", "Женский");
        List<KeyboardButton> secondRowButtons = setUpButtons("Функции");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons);
    }

    public static void setButtonsAge(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("16-18", "18-35", "35-50", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons);
    }

    public static void setButtonsHeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("150-160", "160-170");
        List<KeyboardButton> secondRowButtons = setUpButtons("170-180", "180-190", "190-200", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons);
    }

    public static void setButtonsWeight(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("40-45", "50-55");
        List<KeyboardButton> secondRowButtons = setUpButtons("55-60", "60-65", "65-70", "70-75");
        List<KeyboardButton> thirdRowButtons = setUpButtons("75-80", "80-90", "90-100", "100-110", "Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons, thirdRowButtons);
    }
    public static void setButtonsActivity(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Минимальный");
        List<KeyboardButton> secondRowButtons = setUpButtons("Низкий");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Средний");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Высокий");
        List<KeyboardButton> fifthRowButtons = setUpButtons("Очень высокий");
        List<KeyboardButton> sixthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, false, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons, fifthRowButtons, sixthRowButtons);
    }

    public static void setButtonsProgram(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Снизить вес");
        List<KeyboardButton> secondRowButtons = setUpButtons("Поддерживать вес");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Набрать вес");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, true, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons);
    }
    public static void setButtonsDiet(SendMessage sendMessage) {
        List<KeyboardButton> firstRowButtons = setUpButtons("Диета для снижения веса");
        List<KeyboardButton> secondRowButtons = setUpButtons("Диета для поддержания веса");
        List<KeyboardButton> thirdRowButtons = setUpButtons("Диета для набора веса");
        List<KeyboardButton> fourthRowButtons = setUpButtons("Назад");
        setUpKeyboard(sendMessage, true, firstRowButtons, secondRowButtons, thirdRowButtons, fourthRowButtons);
    }

}
