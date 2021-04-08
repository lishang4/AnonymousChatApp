package chat.lishang4.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import chat.lishang4.com.model.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // TODO: create a class that extends GroupAdapter,
    //  overwrite inside to make scrollToBottom function easily to implemented.
    private val messageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = messageAdapter
        populateMessage()

        // TODO: clickListener should be in another kt file,
        //  here should br just doing websocket thing
        messageBtn.setOnClickListener {
            val message = Message(text = editText.text.toString(), sendBy = "me")
            val sendMessageItem = SendMessageItem(message)

            // TODO: send message to websocket server first, if success then add msg to adapter
            messageAdapter.add(sendMessageItem)
            editText.text.clear()

            // mock receive
            receiveAutoResponse()
            scrollToBottom()
        }
    }

    private fun populateMessage() {
        val messages = listOf<Message>()
        messages.forEach {
            if (it.sendBy == "me") {
                messageAdapter.add(SendMessageItem(it))
            } else {
                messageAdapter.add(ReceiveMessageItem(it))
            }
        }
    }

    private fun receiveAutoResponse() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            // TODO: connect with websocket server
            val receive = Message(text = "test", sendBy = "me")
            val receiveItem = ReceiveMessageItem(receive)

            messageAdapter.add(receiveItem)
        }
    }

    private fun scrollToBottom(){
        // TODO: implement funciton
    }
}