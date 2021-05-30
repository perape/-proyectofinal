package edu.itesm.proyecto_final_prototipo

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_prueba_audiometria.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Prueba_audiometria.newInstance] factory method to
 * create an instance of this fragment.
 */
class Prueba_audiometria : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var bandera=0
    var audiocontador=1
    var reprstop=1
    var frequencia=125
    lateinit var mp:MediaPlayer
    lateinit var resultadoAudiometria: ResultadoAudiometria


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prueba_audiometria, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mp = MediaPlayer.create(context, R.raw.senoidal_125hz)
        bandera=audiocontador
        button14.text="Pasar a frequencia 250 Hz"
        button15.text="Pasar a frequencia 8000 Hz"
        textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
        button13.setOnClickListener {
            if (bandera!=audiocontador){
                if(audiocontador==1){
                    mp = MediaPlayer.create(context, R.raw.senoidal_125hz)
                    bandera=audiocontador
                    frequencia=125
                }else if(audiocontador==2){
                    mp = MediaPlayer.create(context, R.raw.senoidal_250hz)
                    bandera=audiocontador
                    frequencia=250
                }else if(audiocontador==3){
                    mp = MediaPlayer.create(context, R.raw.senoidal_500hz)
                    bandera=audiocontador
                    frequencia=500
                }else if(audiocontador==4){
                    mp = MediaPlayer.create(context, R.raw.senoidal_1000hz)
                    bandera=audiocontador
                    frequencia=1000
                }else if(audiocontador==5){
                    mp = MediaPlayer.create(context, R.raw.senoidal_2000hz)
                    bandera=audiocontador
                    frequencia=2000
                }else if(audiocontador==6){
                    mp = MediaPlayer.create(context, R.raw.senoidal_4000hz)
                    bandera=audiocontador
                    frequencia=4000
                }else if(audiocontador==7){
                    mp = MediaPlayer.create(context, R.raw.senoidal_8000hz)
                    bandera=audiocontador
                    frequencia=80000
                }
            }
            if (reprstop==1){
                mp.start()
                button13.text="Detener"
                textView.text="Aumenta o disminuye el volumen de tu dispositivo hasta que apenas alcances a escuchar el tono puro, despues presiona el boton de Deneter y pasa o regresar a la siguiente frequencia"
                reprstop=0
            }else{
                mp.pause()
                button13.text="Reproducir"
                textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
                reprstop=1
            }
        }
        button14.setOnClickListener {
            if (reprstop!=1){
                mp.pause()
                button13.text="Reproducir"
                textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
                reprstop=1
            }
            mp.stop()
            audiocontador=++audiocontador
            button13.text="Reproducir"
            if (audiocontador>=8){
                audiocontador=1
            }
            if(audiocontador==1){
                button14.text="Pasar a frequencia 250 Hz"
                button15.text="Regresar a frequencia 8000 Hz"
                frequencia=125

            }else if(audiocontador==2){
                button14.text="Pasar a frequencia 500 Hz"
                button15.text="Regresar a frequencia 125 Hz"
                frequencia=250
            }else if(audiocontador==3){
                button14.text="Pasar a frequencia 1000 Hz"
                button15.text="Regresar a frequencia 250 Hz"
                frequencia=500
            }else if(audiocontador==4){
                button14.text="Pasar a frequencia 2000 Hz"
                button15.text="Regresar a frequencia 500 Hz"
                frequencia=1000
            }else if(audiocontador==5){
                button14.text="Pasar a frequencia 4000 Hz"
                button15.text="Regresar a frequencia 1000 Hz"
                frequencia=2000
            }else if(audiocontador==6){
                button14.text="Pasar a frequencia 8000 Hz"
                button15.text="Regresar a frequencia 2000 Hz"
                frequencia=4000
            }else if(audiocontador==7){
                button14.text="Pasar a frequencia 125 Hz"
                button15.text="Regresar a frequencia 4000 Hz"
                frequencia=8000
            }
            textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
        }
        button15.setOnClickListener {
            mp.stop()
            audiocontador=--audiocontador
            button13.text="Reproducir"
            if (audiocontador<=0){
                audiocontador=7
            }
            if(audiocontador==1){
                button14.text="Pasar a frequencia 250 Hz"
                button15.text="Regresar a frequencia 8000 Hz"
                frequencia=125
            }else if(audiocontador==2){
                button14.text="Pasar a frequencia 500 Hz"
                button15.text="Regresar a frequencia 125 Hz"
                frequencia=250
            }else if(audiocontador==3){
                button14.text="Pasar a frequencia 1000 Hz"
                button15.text="Regresar a frequencia 250 Hz"
                frequencia=500
            }else if(audiocontador==4){
                button14.text="Pasar a frequencia 2000 Hz"
                button15.text="Regresar a frequencia 500 Hz"
                frequencia=1000
            }else if(audiocontador==5){
                button14.text="Pasar a frequencia 4000 Hz"
                button15.text="Regresar a frequencia 1000 Hz"
                frequencia=2000
            }else if(audiocontador==6){
                button14.text="Pasar a frequencia 8000 Hz"
                button15.text="Regresar a frequencia 2000 Hz"
                frequencia=4000
            }else if(audiocontador==7){
                button14.text="Pasar a frequencia 125 Hz"
                button15.text="Regresar a frequencia 4000 Hz"
                frequencia=8000
            }
            textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
        }
        button16.setOnClickListener{

            val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
            val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
            val max = am?.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            Log.i("holis","$music_volume_level")
            Log.i("holis","$max")
        }


    }
    fun onClick() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Prueba_audiometria.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Prueba_audiometria().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}