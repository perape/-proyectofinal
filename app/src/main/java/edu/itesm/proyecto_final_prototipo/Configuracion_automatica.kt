package edu.itesm.proyecto_final_prototipo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.fragment_configuracion_automatica.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Configuracion_automatica.newInstance] factory method to
 * create an instance of this fragment.
 */
class Configuracion_automatica : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var bluetoothJhr: BluetoothJhr
    var initConexion = false
    var offHilo = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        return inflater.inflate(R.layout.fragment_configuracion_automatica, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button9.setOnClickListener {

            val intent = Intent(activity, Main2Activity::class.java)
            startActivity(intent)
        }
        button10.setOnClickListener {
            var Rx: String = dato.text.toString()
            bluetoothJhr.mTx(Rx)
            Log.i("holis","$Rx")
        }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Configuracion_automatica.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Configuracion_automatica().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}