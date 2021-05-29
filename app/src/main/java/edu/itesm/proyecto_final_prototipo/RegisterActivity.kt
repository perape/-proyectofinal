package edu.itesm.proyecto_final_prototipo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.itesm.proyecto_final_prototipo.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)

        auth = Firebase.auth
        setRegister()
    }

    private fun setRegister() {

        bind.registerbtn.setOnClickListener {

            if (bind.correo.text.isNotEmpty() && bind.password.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(

                    bind.correo.text.toString(),
                    bind.password.text.toString()

                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        usuarioCreado()

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

                }


            }
        }
    }
    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        finish()
    }

    private fun usuarioCreado() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("successful")
            setMessage("Usuario creado con éxito!")
            setPositiveButton("ok", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }

    }
}