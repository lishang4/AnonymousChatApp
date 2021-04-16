package chat.lishang4.com

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chat.lishang4.com.model.Message
import chat.lishang4.com.model.MessagesAdapter as Messages
import chat.lishang4.com.model.ServerConnection
import chat.lishang4.com.model.ServerConnection.ConnectionStatus
import chat.lishang4.com.model.ServerConnection.ServerListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity(), ServerListener {
    private var messages = Messages()
    private val SERVER_URL = "ws://192.168.3.105:8765"
    private lateinit var mServerConnection : ServerConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()

        serverConnect()
    }

    private fun serverConnect(){
        mServerConnection = ServerConnection(SERVER_URL)
        println("[Customize] connecting to$SERVER_URL")
    }

    private fun setRecyclerView(){
        recyclerView.adapter = messages.adapter
        messages.populate()
    }

    fun messageBtnListener(v: View){
        messages.adapter.add(
                SendMessageItem(
                        Message(text = editText.text.toString(), sendBy = "user")
                )
        )
        sendMessage(editText.text.toString())
        editText.text.clear()
        scrollToBottom()
    }

    private fun receiveMessage(message: String) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            messages.adapter.add(
                    ReceiveMessageItem(
                            Message(text = message, sendBy = "receiver")
                    )
            )
            println("Sending Response Text: $message")
            scrollToBottom()
        }
    }

    private fun scrollToBottom(){
        recyclerView.scrollToPosition(messages.adapter.itemCount - 1)
    }

    override fun onResume() {
        super.onResume()
        mServerConnection!!.connect(this)
    }

    override fun onPause() {
        super.onPause()
        mServerConnection!!.disconnect()
    }

    private fun sendMessage(message: String) {
        mServerConnection?.sendMessage(message)
    }

    override fun onNewMessage(message: String) {
        println("New Message From Server: $message")
        receiveMessage(message)
    }

    override fun onStatusChange(status: ConnectionStatus) {
        println("statusMsg"+ ConnectionStatus.CONNECTED.toString())
    }
}
