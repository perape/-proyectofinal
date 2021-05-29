package edu.itesm.proyecto_final_prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.itesm.proyecto_final_prototipo.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var bind : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        auth = Firebase.auth
        setLogin()

    }

    override fun onStart() {
        super.onStart()
        val usuarioActivo = auth.currentUser
        if (usuarioActivo != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    private fun setLogin() {
        bind.loginbtn.setOnClickListener {
            if (bind.correo.text.isNotEmpty() && bind.password.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    bind.correo.text.toString(),
                    bind.password.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this,"Bienvenido a Homp!",
                            Toast.LENGTH_LONG).show()
                        val intento = Intent(this, MainActivity::class.java)
                        startActivity(intento)
                        finish()
                    }else{
                        Toast.makeText(this,"Error en los datos!",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        registerbtn.setOnClickListener {
            val intento = Intent(this, RegisterActivity::class.java)
            startActivity(intento)
        }
    }


}