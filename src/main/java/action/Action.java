package action;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Action {
    void execute(Message message);
}
