package com.example.wtoggle

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkConnectionStatus = findViewById<TextView>(R.id.tv)
        Thread(Runnable {
            while(true)
            {
                var conStat:String = "Not Connected"

                val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = cm.allNetworkInfo
                for (ni in netInfo) {
                    if (ni.typeName.equals("WIFI", ignoreCase = true)) if (ni.isConnected) conStat = "WIFI"
                    if (ni.typeName.equals("MOBILE", ignoreCase = true)) if (ni.isConnected) conStat = "MOBILE DATA"
                }
                runOnUiThread{
                    networkConnectionStatus.text = conStat
                }
            }
        }).start()
    }
}