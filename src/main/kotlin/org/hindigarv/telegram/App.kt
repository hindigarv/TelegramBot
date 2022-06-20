package org.hindigarv.telegram

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val api = TelegramBotsApi(DefaultBotSession::class.java)
    api.registerBot(HindiGarvBot())
    println("Bot Started")
}