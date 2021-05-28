package edu.itesm.proyecto_final_prototipo

data class ResultadoAudiometria(
    var resultado:List<frequencia>
)

data class frequencia(
    var freq125:Int,
    var freq250:Int,
    var freq580:Int,
    var freq1000:Int,
    var freq2000:Int,
    var freq4000:Int,
    var freq8000:Int,
    )