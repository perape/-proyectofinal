package edu.itesm.proyecto_final_prototipo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this, "Seleccione el dispositivo a conectar", Toast.LENGTH_SHORT).show()
        val blue = BluetoothJhr(this, disp2, MainActivity2::class.java)
        blue.onBluetooth()
        disp2.setOnItemClickListener { parent, view, position, id ->
            blue.bluetoothSeleccion(position)
            Toast.makeText(this, "Se ha conectado un dispositivo", Toast.LENGTH_SHORT).show()
        }


    }
}