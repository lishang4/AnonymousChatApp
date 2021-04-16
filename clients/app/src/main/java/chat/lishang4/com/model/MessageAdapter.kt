package chat.lishang4.com.model

import chat.lishang4.com.ReceiveMessageItem
import chat.lishang4.com.SendMessageItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

class MessagesAdapter {
    val adapter = GroupAdapter<GroupieViewHolder>()

    fun populate() {
        val messages = listOf<Message>()
        messages.forEach {
            if (it.sendBy == "user") {
                adapter.add(SendMessageItem(it))
            } else {
                adapter.add(ReceiveMessageItem(it))
            }
        }
    }
}