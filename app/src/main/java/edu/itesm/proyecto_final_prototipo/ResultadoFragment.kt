package edu.itesm.proyecto_final_prototipo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_prueba_audiometria.*
import kotlinx.android.synthetic.main.fragment_resultado.*


class ResultadoFragment : Fragment() {

    private val args by navArgs<ResultadoFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultado, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView2.text = args.resultado.fecha

        val xvalue=ArrayList<String>()
        xvalue.add("125 Hz")
        xvalue.add("250 Hz")
        xvalue.add("500 Hz")
        xvalue.add("1000 Hz")
        xvalue.add("2000 Hz")
        xvalue.add("4000 Hz")
        xvalue.add("8000 Hz")

        var lineentry = ArrayList<Entry>();
        lineentry.add(Entry(args.resultado.v125!!.toFloat(), 0))
        lineentry.add(Entry(args.resultado.v250!!.toFloat(), 1))
        lineentry.add(Entry(args.resultado.v500!!.toFloat(), 2))
        lineentry.add(Entry(args.resultado.v1000!!.toFloat(), 3))
        lineentry.add(Entry(args.resultado.v2000!!.toFloat(), 4))
        lineentry.add(Entry(args.resultado.v4000!!.toFloat(), 5))
        lineentry.add(Entry(args.resultado.v8000!!.toFloat(), 6))
        val lineadataset = LineDataSet(lineentry, "Volumen de equipo")
        lineadataset.color=resources.getColor(R.color.blue)
        val data = LineData(xvalue, lineadataset)
        LineChart2.data=data
        LineChart2.setBackgroundColor(resources.getColor(R.color.silver))
    }

}