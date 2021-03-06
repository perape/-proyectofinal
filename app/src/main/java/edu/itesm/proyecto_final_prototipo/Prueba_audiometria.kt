package edu.itesm.proyecto_final_prototipo

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import java.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_prueba_audiometria.*


class Prueba_audiometria : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var bundle: Bundle


    var bandera=0
    var audiocontador=1
    var reprstop=1
    var frequencia=125
    lateinit var mp:MediaPlayer
    lateinit var resultadoAudiometria: ResultadoAudiometria
    var level_music_125Hz:Int?=0
    var level_music_250Hz:Int?=0
    var level_music_500Hz:Int?=0
    var level_music_1000Hz:Int?=0
    var level_music_2000Hz:Int?=0
    var level_music_4000Hz:Int?=0
    var level_music_8000Hz:Int?=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("resultados")

        analytics = FirebaseAnalytics.getInstance(context)

        bundle = Bundle()

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
                textView.text="Aumenta o disminuye el volumen de tu dispositivo hasta que apenas alcances a escuchar el tono puro, despues presiona el boton de Deneter y pasar o regresar alguna de las de las frequencia"
                reprstop=0
            }else{
                mp.pause()
                button13.text="Reproducir"
                textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
                reprstop=1
                if(audiocontador==1){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_125Hz=music_volume_level
                }else if(audiocontador==2){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_250Hz=music_volume_level
                }else if(audiocontador==3){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_500Hz=music_volume_level
                }else if(audiocontador==4){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_1000Hz=music_volume_level
                }else if(audiocontador==5){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_2000Hz=music_volume_level
                }else if(audiocontador==6){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_4000Hz=music_volume_level
                }else if(audiocontador==7){
                    val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                    val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                    level_music_8000Hz=music_volume_level
                }
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
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_125Hz=music_volume_level
            }else if(audiocontador==2){
                button14.text="Pasar a frequencia 500 Hz"
                button15.text="Regresar a frequencia 125 Hz"
                frequencia=250
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_250Hz=music_volume_level
            }else if(audiocontador==3){
                button14.text="Pasar a frequencia 1000 Hz"
                button15.text="Regresar a frequencia 250 Hz"
                frequencia=500
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_500Hz=music_volume_level
            }else if(audiocontador==4){
                button14.text="Pasar a frequencia 2000 Hz"
                button15.text="Regresar a frequencia 500 Hz"
                frequencia=1000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_1000Hz=music_volume_level
            }else if(audiocontador==5){
                button14.text="Pasar a frequencia 4000 Hz"
                button15.text="Regresar a frequencia 1000 Hz"
                frequencia=2000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_2000Hz=music_volume_level
            }else if(audiocontador==6){
                button14.text="Pasar a frequencia 8000 Hz"
                button15.text="Regresar a frequencia 2000 Hz"
                frequencia=4000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_4000Hz=music_volume_level
            }else if(audiocontador==7){
                button14.text="Pasar a frequencia 125 Hz"
                button15.text="Regresar a frequencia 4000 Hz"
                frequencia=8000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_8000Hz=music_volume_level
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
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_125Hz=music_volume_level
            }else if(audiocontador==2){
                button14.text="Pasar a frequencia 500 Hz"
                button15.text="Regresar a frequencia 125 Hz"
                frequencia=250
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_250Hz=music_volume_level
            }else if(audiocontador==3){
                button14.text="Pasar a frequencia 1000 Hz"
                button15.text="Regresar a frequencia 250 Hz"
                frequencia=500
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_500Hz=music_volume_level
            }else if(audiocontador==4){
                button14.text="Pasar a frequencia 2000 Hz"
                button15.text="Regresar a frequencia 500 Hz"
                frequencia=1000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_1000Hz=music_volume_level
            }else if(audiocontador==5){
                button14.text="Pasar a frequencia 4000 Hz"
                button15.text="Regresar a frequencia 1000 Hz"
                frequencia=2000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_2000Hz=music_volume_level
            }else if(audiocontador==6){
                button14.text="Pasar a frequencia 8000 Hz"
                button15.text="Regresar a frequencia 2000 Hz"
                frequencia=4000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_4000Hz=music_volume_level
            }else if(audiocontador==7){
                button14.text="Pasar a frequencia 125 Hz"
                button15.text="Regresar a frequencia 4000 Hz"
                frequencia=8000
                val am = activity?.getSystemService(AUDIO_SERVICE) as AudioManager?
                val music_volume_level = am?.getStreamVolume(AudioManager.STREAM_MUSIC)
                level_music_8000Hz=music_volume_level
            }
            textView.text="Presiona el Boton de Reproducir para comenzar a escuhar el tonos puros de $frequencia Hz de frequencia"
        }
        button16.setOnClickListener{

            Log.i("holis", "$level_music_125Hz")
            Log.i("holis", "$level_music_250Hz")
            Log.i("holis", "$level_music_500Hz")
            Log.i("holis", "$level_music_1000Hz")
            Log.i("holis", "$level_music_2000Hz")
            Log.i("holis", "$level_music_4000Hz")
            Log.i("holis", "$level_music_8000Hz")
            val xvalue=ArrayList<String>()
            xvalue.add("125 Hz")
            xvalue.add("250 Hz")
            xvalue.add("500 Hz")
            xvalue.add("1000 Hz")
            xvalue.add("2000 Hz")
            xvalue.add("4000 Hz")
            xvalue.add("8000 Hz")

            var lineentry = ArrayList<Entry>();
            lineentry.add(Entry(level_music_125Hz!!.toFloat(), 0))
            lineentry.add(Entry(level_music_250Hz!!.toFloat(), 1))
            lineentry.add(Entry(level_music_500Hz!!.toFloat(), 2))
            lineentry.add(Entry(level_music_1000Hz!!.toFloat(), 3))
            lineentry.add(Entry(level_music_2000Hz!!.toFloat(), 4))
            lineentry.add(Entry(level_music_4000Hz!!.toFloat(), 5))
            lineentry.add(Entry(level_music_8000Hz!!.toFloat(), 6))
            val lineadataset = LineDataSet(lineentry, "Volumen de equipo")
            lineadataset.color=resources.getColor(R.color.blue)
            val data = LineData(xvalue, lineadataset)
            LineChart.data=data
            LineChart.setBackgroundColor(resources.getColor(R.color.silver))
            textView13.text="El resultado de tu audiometria se muestra a continuacion, mientras menor volumen del equipo necesitates para percibir el tono puro, mejor capacitad auditivas tienes"
        }

        button4.setOnClickListener { addResultados() }



    }

    public fun addResultados(){
        val text = "Se agregaron resultados"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)

        val Fecha = Calendar.getInstance().getTime()

        val v125 = level_music_125Hz
        val v250 = level_music_250Hz
        val v500 = level_music_500Hz
        val v1000 = level_music_1000Hz
        val v2000 = level_music_2000Hz
        val v4000 = level_music_4000Hz
        val v8000 = level_music_8000Hz

        val usuario = Firebase.auth.currentUser
        reference = database.getReference("resultados/${usuario.uid}")
        val id = reference.push().key
        val resultado = Resultados(
            id.toString(),
            Fecha.toString(),
            v125.toString(),
            v250.toString(),
            v500.toString(),
            v1000.toString(),
            v2000.toString(),
            v4000.toString(),
            v8000.toString()
        )

        reference.child(id!!).setValue(resultado)
        toast.show()

        bundle.putString("edu_itesm_proyecto_final_prototipo", "added_resultado")
        analytics.logEvent("main", bundle)
    }
}