package edu.itesm.proyecto_final_prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class menu_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_1)

        Handler().postDelayed({
        startActivity(Intent(this,LoginActivity::class.java))

        // close this activity
        finish()
    }, 3000)

    }
}