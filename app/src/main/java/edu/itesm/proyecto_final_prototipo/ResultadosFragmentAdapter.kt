package edu.itesm.proyecto_final_prototipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ResultadosFragmentAdapter(
    private val data: MutableList<Resultados>
    ) : RecyclerView.Adapter<ResultadosFragmentAdapter.ViewHolder>()  {


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(property: Resultados){
            val fecha = view.findViewById<TextView>(R.id.Fecha)


            fecha.text = property.fecha


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
        val item = data[position]
        holder.bind(data!![position])

        holder.itemView.setOnClickListener {
            val action = Historial_audiometriaDirections.actionHistorialAudiometriaToResultadoFragment(item)
            holder?.itemView.findNavController().navigate(action)

        }
    }

    fun deleteItem(index:Int){
        data?.removeAt(index)
        notifyDataSetChanged()
    }

    fun getItem(index: Int): Resultados? {
        return data?.get(index)
    }

}