package edu.itesm.proyecto_final_prototipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultadosFragmentAdapter(private val data: MutableList<Resultados>?) : RecyclerView.Adapter<ResultadosFragmentAdapter.ViewHolder>()  {


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(property: Resultados){
            val fecha = view.findViewById<TextView>(R.id.Fecha)
            val v125 = view.findViewById<TextView>(R.id.v125)
            val v250 = view.findViewById<TextView>(R.id.v250)
            val v500 = view.findViewById<TextView>(R.id.v500)
            val v1000 = view.findViewById<TextView>(R.id.v1000)
            val v2000 = view.findViewById<TextView>(R.id.v2000)
            val v4000 = view.findViewById<TextView>(R.id.v4000)
            val v8000 = view.findViewById<TextView>(R.id.v8000)


            fecha.text = property.fecha
            v125.text = property.v125
            v250.text = property.v250
            v500.text = property.v500
            v1000.text = property.v1000
            v2000.text = property.v2000
            v4000.text = property.v4000
            v8000.text = property.v8000
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.audio_renglon, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])

    }

    fun deleteItem(index:Int){
        data?.removeAt(index)
        notifyDataSetChanged()
    }

    fun getItem(index: Int): Resultados? {
        return data?.get(index)
    }

}