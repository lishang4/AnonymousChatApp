package chat.lishang4.com

import chat.lishang4.com.databinding.ItemMessageSendBinding
import chat.lishang4.com.model.Message
import com.xwray.groupie.databinding.BindableItem

class SendMessageItem(private val message: Message) : BindableItem<ItemMessageSendBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_message_send
    }

    override fun bind(viewBinding: ItemMessageSendBinding, position: Int) {
        viewBinding.message = message
    }
}