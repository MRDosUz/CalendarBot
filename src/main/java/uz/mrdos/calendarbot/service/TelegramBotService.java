package uz.mrdos.calendarbot.service;

import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mrdos.calendarbot.configuration.BotConfiguration;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

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
    public void onRegister() {
        System.out.println("12313213131");
    }

    @Override
    public void onUpdateReceived(Update update) {

//        System.out.println("123");
        Update newUpdate = update;
//        if (update.hasMessage() && update.getMessage().hasText()){
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//            switch (messageText){
//                case "/start":
//                    startCommonReceived(chatId, update.getMessage().getChat().getFirstName());
//                    break;
//                default:
//                    sendMessage(chatId, "Sorry, command was not recognized.");
//
//            }
//
//            SendMessage message = new SendMessage(); // Create a message object object
//            message.setChatId(update.getMessage().getChatId());
//            message.setText("You send /start");
//            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//            List<InlineKeyboardButton> rowInline = new ArrayList<>();
//            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//            inlineKeyboardButton.setText("Update message text");
//            inlineKeyboardButton.setCallbackData("update_msg_text");
//            rowInline.add(inlineKeyboardButton);
//            // Set the keyboard to the markup
//            rowsInline.add(rowInline);
//            // Add it to the message
//            markupInline.setKeyboard(rowsInline);
//            message.setReplyMarkup(markupInline);
//            message.setChatId(chatId);
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }else if (update.hasCallbackQuery()) {
//            // Set variables
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//
//            if (call_data.equals("update_msg_text")) {
//                String answer = "Updated message text";
//                EditMessageText new_message = new EditMessageText();
//                new_message.setChatId(chat_id);
//                new_message.setMessageId(toIntExact(message_id));
//                new_message.setText(answer);
//                try {
//                    execute(new_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //**********************************************
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {


                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                message.setText("You send /start");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                List<InlineKeyboardButton> rowInline = new ArrayList<>();

//                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//                inlineKeyboardButton.setText("Update message text");
//                inlineKeyboardButton.setCallbackData("update_msg_text");
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("1");
                inlineKeyboardButton.setCallbackData("update_msg_text");
                rowInline.add(inlineKeyboardButton);

                InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                inlineKeyboardButton2.setText("2");
                inlineKeyboardButton2.setCallbackData("update_msg_text");
                rowInline.add(inlineKeyboardButton2);

                InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
                inlineKeyboardButton3.setText("3");
                inlineKeyboardButton3.setCallbackData("update_msg_text");
                rowInline.add(inlineKeyboardButton3);

                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);

                message.setReplyMarkup(markupInline);

                int k = 0;
                SendMessage newMessage = new SendMessage();
                newMessage.setChatId(chat_id);
                newMessage.setText("You send 0️⃣0️⃣ /start");
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rows = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    List<InlineKeyboardButton> buttons = new ArrayList<>();
                    for (int j = 0; j < 3; j++) {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setCallbackData("update_msg_text");
                        if (i == 3){
                            button.setText(j == 0 ? "✅" : j == 1 ? "0" : "\uD83D\uDD19");
//                            buttons.add(0,button);
                        }else {
                            button.setText(String.valueOf(++k));
                        }
                        buttons.add(button);

                    }
                    rows.add(buttons);
                }

                inlineKeyboardMarkup.setKeyboard(rows);
                newMessage.enableMarkdown(true);
                newMessage.setReplyMarkup(inlineKeyboardMarkup);
                try {
                    execute(newMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(chat_id);
                new_message.setMessageId(toIntExact(message_id));
                new_message.setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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
