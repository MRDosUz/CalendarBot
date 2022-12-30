package uz.mrdos.calendarbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mrdos.calendarbot.configuration.BotConfiguration;

@Component
public class TelegramBotService extends TelegramLongPollingBot {

    final BotConfiguration configuration;

    public TelegramBotService(BotConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public String getBotUsername() {
        return configuration.getBotName();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":
                    startCommonReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized.");

            }
        }
    }

    private void startCommonReceived(long chatId, String name){
        String answer = "Hi, " + name + "! Nice to meet You. ";
        sendMessage(chatId, answer);
    }

    public void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
        }catch (TelegramApiException ignored){

        }
    }
}
