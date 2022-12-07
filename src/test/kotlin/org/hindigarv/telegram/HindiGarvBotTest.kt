package org.hindigarv.telegram

import org.junit.jupiter.api.Test
import org.telegram.telegrambots.meta.api.objects.Update


internal class HindiGarvBotTest {

    @Test
    fun onUpdateReceived_should_ignore_when_there_is_no_message() {
        val update = Update()
        val bot = HindiGarvBot()
        bot.onUpdateReceived(update)
    }
}