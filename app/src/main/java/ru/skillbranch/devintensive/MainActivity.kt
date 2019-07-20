package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var benderImage:ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn:ImageView

    lateinit var benderObj:Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "OnCreate")

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS")?:Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?:Bender.Question.NAME.name
        val message = savedInstanceState?.getString("MESSAGE","")
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        messageEt.setText(message)

        val(r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

        sendBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send){
            val(phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val(r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
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
}
