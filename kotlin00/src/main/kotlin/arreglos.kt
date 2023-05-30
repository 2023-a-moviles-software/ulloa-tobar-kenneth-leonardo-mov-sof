fun main() {
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    var arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    arregloEstatico.forEach {
        valor: Int ->
            println(valor)
    }

    arregloDinamico.forEach {
        println(it)
    }
    arregloDinamico.forEachIndexed { index, i -> println("${i} en la posición ${index}") }
    val respuestaMap = arregloDinamico.map {
        it -> return@map it.toDouble() + 100.0
    }
    println(respuestaMap)

    val filtrado = arregloEstatico.filter { it -> it < 3 }

    println(filtrado)

    //OR AND
    //OR Any cumple una condicion
    //AND All todos cumplen una condicion
    val resp = arregloEstatico.any{
        it ->  it > 3
    } //true

    val resp2 = arregloEstatico.all{
        it -> it > 3
    } // false

    //reduce suma con acumulador (variable temporal)
    val respReduce: Int = arregloDinamico.reduce{ acumulado: Int, valor: Int ->
        return@reduce (acumulado + valor)
    }
    
}
