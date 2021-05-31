package edu.itesm.proyecto_final_prototipo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import edu.itesm.proyecto_final_prototipo.databinding.FragmentHistorialAudiometriaBinding


class Historial_audiometria : Fragment() {

    private lateinit var bind: FragmentHistorialAudiometriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentHistorialAudiometriaBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargaDatos()
    }

    fun cargaDatos() {
        var listaResultados = ArrayList<Resultados>()
        var reference: DatabaseReference
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usuario = Firebase.auth.currentUser
        reference = database.getReference("resultados/${usuario.uid}")
        activity?.let {
            bind.list.apply {
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        for (resultados in snapshot.children) {
                            var id =resultados.child("id").value.toString()
                            var fecha = resultados.child("fecha").value.toString()
                            var v125 = resultados.child("v125").value.toString()
                            var v250 = resultados.child("v250").value.toString()
                            var v500 = resultados.child("v500").value.toString()
                            var v1000 = resultados.child("v1000").value.toString()
                            var v2000 = resultados.child("v2000").value.toString()
                            var v4000 = resultados.child("v4000").value.toString()
                            var v8000 = resultados.child("v8000").value.toString()


                            val newResultados =
                                Resultados(id ,fecha, v125, v250, v500, v1000, v2000, v4000, v8000)
                            Log.i("value", newResultados.toString())
                            listaResultados.add(newResultados)

                        }

                        layoutManager = LinearLayoutManager(activity)
                        adapter = ResultadosFragmentAdapter(listaResultados)

                        /*val item = object : SwipeToDelete(
                            getContext(),
                            ItemTouchHelper.UP, ItemTouchHelper.LEFT
                        ) {
                            override fun onSwiped(
                                viewHolder: RecyclerView.ViewHolder,
                                direction: Int
                            ) {
                                super.onSwiped(viewHolder, direction)
                                val comic = listaComics[viewHolder.adapterPosition]

                                borraDatos(comic)
                            }
                        }*/
                       // val itemTouchHelper = ItemTouchHelper(item)
                        //itemTouchHelper.attachToRecyclerView(bind.list)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        //Toast.makeText(this@CartFragment,"Error al obtener datos",Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

}