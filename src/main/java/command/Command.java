package command;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    void sendMessage(Message message, String text);
}
