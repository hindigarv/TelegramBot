package org.hindigarv.telegram

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(App().greeting)
    val api = TelegramBotsApi(DefaultBotSession::class.java)
    api.registerBot(HindiGarvBot())
}