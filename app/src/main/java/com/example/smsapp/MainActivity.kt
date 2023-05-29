package com.example.smsapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    lateinit var phoneEdt: EditText
    lateinit var messageEdt: EditText
    lateinit var sendMsgBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneEdt = findViewById(R.id.idEdtPhone)
        messageEdt = findViewById(R.id.idEdtMessage)
        sendMsgBtn = findViewById(R.id.idBtnSendMessage)

        sendMsgBtn.setOnClickListener {

            val phoneNumber = phoneEdt.text.toString()
            val message = messageEdt.text.toString()

            try {

                val smsManager: SmsManager
                if (Build.VERSION.SDK_INT>=23) {
                    smsManager = this.getSystemService(SmsManager::class.java)
                }
                else{

                    smsManager = SmsManager.getDefault()
                }
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)

                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {

                Toast.makeText(applicationContext, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}