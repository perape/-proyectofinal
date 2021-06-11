package edu.itesm.proyecto_final_prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity2 : AppCompatActivity() {
    lateinit var bluetoothJhr: BluetoothJhr
    var initConexion = false
    var offHilo = false
    var volumeninicio =0
    var volumenfinal = 0
    var freqinicio =101
    var freqfinal = 101
    var Rx: String = "3"
    var Rx2: String = "101"
    var freqEntero:Int=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        bluetoothJhr = BluetoothJhr(this,Main2Activity::class.java)
        bluetoothJhr.exitErrorOk(true)
        bluetoothJhr.mensajeConexion("Conectado")
        bluetoothJhr.mensajeErrorTx("problamas en la conexion")

        buttonconectorblue.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

        enviarinfo.setOnClickListener {
            bluetoothJhr.mTx(Rx)
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(Rx2)
        }

        Volumen1.max=100
        Volumen1.min=0
        Volumen1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView17.text = progress.toString()
                if(progress==100){
                    Rx="z"
                }else{
                    Rx=(progress/10).toString()
                }

                //Log.i("holis","$Rx")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar !=null){
                    volumeninicio=seekBar.progress

                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar !=null){
                    volumenfinal=seekBar.progress
                }
            }
        })
        Frecuencia1.max=100
        Frecuencia1.min=1
        Frecuencia1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                freqEntero= ((4000*progress)/100+100)
                textView3.text = freqEntero.toString()+" Hz"
                if(freqEntero>=101 && freqEntero<=187){
                    Rx2="q"
                }
                if(freqEntero>=188 && freqEntero<=375){
                    Rx2="w"
                }
                if(freqEntero>=376 && freqEntero<=850){
                    Rx2="e"
                }
                if(freqEntero>=856 && freqEntero<=1500){
                    Rx2="r"
                }
                if(freqEntero>=1501 && freqEntero<=3000){
                    Rx2="t"
                }
                if(freqEntero>=3000 && freqEntero<=4061){
                    Rx2="y"
                }
                Rx2=freqEntero.toString()
                //Toast.makeText(context, "$progress", Toast.LENGTH_LONG).show()
                //Log.i("holis","$freqEntero")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar !=null){
                    freqinicio=seekBar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar !=null){
                    freqfinal=seekBar.progress
                }
            }

        })
    }

    override fun onResume() {
        initConexion=bluetoothJhr.conectaBluetooth()
        super.onResume()
    }

    override fun onPause() {
        offHilo=true
        bluetoothJhr.exitConexion()
        super.onPause()
    }
}