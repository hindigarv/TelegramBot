package org.hindigarv.telegram

import org.hindigarv.core.WordFinder
import org.hindigarv.core.model.Word
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class HindiGarvBot : TelegramLongPollingBot() {
    private val wordFinder = WordFinder()

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

        val words = wordFinder.find(text)
        val reply = prepareReply(words)

        println("message from ${receivedMessage.from.firstName} ${receivedMessage.from.lastName} " +
                "(${receivedMessage.from.id}), " +
                "text => \"${text}\", " +
                "reply =>  \"${reply.replace("\n", "\\n")}\"")

        try {
            execute(SendMessage(chatId.toString(), reply)) // send the message
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun prepareReply(words: List<Word>): String {
        if (words.isEmpty()) {
            return "\uD83D\uDC4D" // 👍
        }
        return words.joinToString("\n") { "${it.shabd} (${it.mool}) -> ${it.paryays.joinToString(", ")}" }
    }
}