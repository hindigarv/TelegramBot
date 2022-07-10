package org.hindigarv.telegram

import mu.KotlinLogging
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val logger = KotlinLogging.logger {}
    val api = TelegramBotsApi(DefaultBotSession::class.java)
    api.registerBot(HindiGarvBot())
    logger.info { "Bot Started" }
}