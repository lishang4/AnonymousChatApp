package chat.lishang4.com

import chat.lishang4.com.databinding.ItemMessageReceiveBinding
import chat.lishang4.com.model.Message
import com.xwray.groupie.databinding.BindableItem

class ReceiveMessageItem(private val message: Message) : BindableItem<ItemMessageReceiveBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_message_receive
    }

    override fun bind(viewBinding: ItemMessageReceiveBinding, position: Int) {
        viewBinding.message = message
    }
}