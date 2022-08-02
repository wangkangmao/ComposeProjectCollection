package com.wangkm.jetsnack.model

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

/**
 * @author: created by wangkm
 * @time: 2022/07/12 12:08
 * @desc：
 * @email: 1240413544@qq.com
 */

data class Message(val id: Long, @StringRes val messageId: Int)

object SnackbarManager {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> get() = _messages.asStateFlow()

    fun showMessage(@StringRes messageTextId: Int) {
        _messages.update { currentMessages ->
            currentMessages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageTextId
            )
        }
    }

    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessage ->
            currentMessage.filterNot { it.id == messageId }
        }
    }

}
