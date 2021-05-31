package edu.itesm.proyecto_final_prototipo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Resultados(
    val id: String, val fecha: String, val v125: String, val v250: String, val v500: String, val v1000: String, val v2000: String,
    val v4000: String, val v8000: String
)
