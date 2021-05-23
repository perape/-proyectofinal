package edu.itesm.proyecto_final_prototipo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_configuracion_manual.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val blue = BluetoothJhr(this, disp2, Configuracion_manual::class.java)
        blue.onBluetooth()
        disp2.setOnItemClickListener { parent, view, position, id ->
            blue.bluetoothSeleccion(position)
        }


    }
}