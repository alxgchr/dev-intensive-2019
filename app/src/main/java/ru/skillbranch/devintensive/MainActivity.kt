package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {


    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "OnCreate")

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val message = savedInstanceState?.getString("MESSAGE", "")
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        messageEt.setText(message)
        messageEt.setOnEditorActionListener(this)

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

        sendBtn.setOnClickListener(this)

        //isKeyboardOpen()

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            sendMsg()
        }
    }

    private fun sendMsg() {
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
        //isKeyboardOpen()
        hideKeyboard()
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "OnStart")
    }

    override fun onRestart() {
        Log.d("M_MainActivity", "OnRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("M_MainActivity", "OnResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d("M_MainActivity", "OnDestroy")
        super.onDestroy()
    }

    override fun onPause() {
        Log.d("M_MainActivity", "OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("M_MainActivity", "OnStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)

        outState?.putString("MESSAGE", messageEt.text.toString())
    }


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (v?.id == R.id.et_message && actionId == EditorInfo.IME_ACTION_DONE) {
            sendMsg()
        }
        return false
    }
}
