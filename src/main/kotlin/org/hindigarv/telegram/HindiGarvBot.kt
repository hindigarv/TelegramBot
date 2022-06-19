package org.hindigarv.telegram

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class HindiGarvBot : TelegramLongPollingBot() {
    override fun getBotUsername(): String {
        return "HindiGarvBot"
    }

    override fun getBotToken(): String {
        return System.getenv("TOKEN")
    }

    override fun onUpdateReceived(update: Update) {
        if (!update.hasMessage()) return  // ignore if update has no message
        val receivedMessage = update.message
        if (!receivedMessage.hasText()) return  // ignore if message has no text
        val text = receivedMessage.text
        val chatId = receivedMessage.chatId

        // prepare a message to send
        val message = SendMessage(chatId.toString(), text)
        try {
            execute(message) // send the message
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}