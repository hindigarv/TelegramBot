package org.hindigarv.telegram

import org.hindigarv.core.WordFinder
import org.hindigarv.core.model.Word
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class HindiGarvBot : TelegramLongPollingBot() {
    private val wordFinder = WordFinder(autoRefresh = true)

    override fun getBotUsername(): String {
        return "HindiGarvBot"
    }

    override fun getBotToken(): String {
        return System.getenv("TOKEN")
    }

    override fun onUpdateReceived(update: Update) {
        if (!update.hasMessage()) return  // ignore if update has no message
        val message = update.message

        // ignore if message has no text
        if (!message.hasText()) return

        // Because we do not want to analyze all the messages, rather only the mentioned ones.
        if (message.isGroupMessage && message.doesNotMentionBot()) return

        val text = message.text
        val chatId = message.chatId

        val words = wordFinder.find(text)
        val reply = prepareReply(words)

        println("message from ${message.from.firstName} ${message.from.lastName} " +
                "(${message.from.id}), " +
                "text => \"${text}\", " +
                "reply =>  \"${reply.replace("\n", "\\n")}\"")

        try {
            val replyMessage = SendMessage(chatId.toString(), reply)
            if (message.isGroupMessage) {
                replyMessage.replyToMessageId = message.messageId
            }
            execute(replyMessage) // send the message
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun Message.doesMentionBot(): Boolean =
        this.entities
            ?.any { it.type == "mention" && it.text == "@HindiGarvBot" }
            ?: false

    private fun Message.doesNotMentionBot(): Boolean = !this.doesMentionBot()

    private fun prepareReply(words: List<Word>): String {
        if (words.isEmpty()) return ".👍"
        return words.joinToString("\n") { "${it.shabd} (${it.mool}) -> ${it.paryays.joinToString(", ")}" }
    }
}