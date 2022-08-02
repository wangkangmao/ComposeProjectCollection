package com.wangkm.jetchat.conversation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import com.wangkm.jetchat.R

/**
 * @author: created by wangkm
 * @time: 2022/07/20 13:12
 * @desc：
 * @email: 1240413544@qq.com
 */

class ConversationUiState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> =
        mutableStateListOf(*initialMessages.toTypedArray())
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg) // Add to the beginning of the list
    }
}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else
)