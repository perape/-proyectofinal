package edu.itesm.proyecto_final_prototipo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.fragment_configuracion_manual.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Configuracion_manual.newInstance] factory method to
 * create an instance of this fragment.
 */
class Configuracion_manual : Fragment() {
    // TODO: Rename and change types of parameters
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

        bluetoothJhr = BluetoothJhr(requireContext(),Main2Activity::class.java)
        bluetoothJhr.exitErrorOk(true)
        bluetoothJhr.mensajeConexion("Conectado")
        bluetoothJhr.mensajeErrorTx("problamas en la conexion")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracion_manual, container, false)
   }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Configuracion_manual.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Configuracion_manual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        button7.setOnClickListener {
            val intent = Intent(activity, Main2Activity::class.java)
            startActivity(intent)
        }
        button8.setOnClickListener {
            //textView17.text = Volumen.toString()


            bluetoothJhr.mTx(Rx)
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(" ")
            bluetoothJhr.mTx(Rx2)
            //Log.i("holis","$Rx2 y $Rx")

            //var Rx2: String = editTextNumber2.text.toString()
        }
        Volumen.max=100
        Volumen.min=0
        Volumen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
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
        Frecuencia.max=100
        Frecuencia.min=1
        Frecuencia.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    override fun onResume() {
        //initConexion=bluetoothJhr.conectaBluetooth()
        super.onResume()
    }

    override fun onPause() {
        offHilo=true
        bluetoothJhr.exitConexion()
        super.onPause()
    }




}