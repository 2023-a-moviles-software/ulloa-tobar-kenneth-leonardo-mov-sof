import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello world");
    val inmutable: String = "Kenneth";
    //inmutable = "Leonardo";
    var mutable: String = "Leonardo";
    mutable = "Kenneth";
    //Duck Typing
    var a = " AA ";
    //Clases JAVA
    val fechaNacimiento: Date = Date();
    var array = arrayOf("String", "Java", 2);
    println(array.toString());
    val estadoCivil = "A";
    when (estadoCivil) {
        ("C") -> {
            println("Estás jodido hermano");
        }
        ("S") -> {
            println("Ya tu sabe");
        }
        else -> {
            println("Y ahora?");
        }
    }
    val esSoltero = (estadoCivil == "S");
    var coqueteo = if (esSoltero) "Si" else "No";

    calcularSueldo(10.00);
    calcularSueldo(10.00, 12.00, 20.00);
    calcularSueldo(10.00, bonoEspecial = 20.00);
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.0, tasa = 14.0);
}
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}");
}
fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100/tasa);
    } else {
        return sueldo * (100/tasa) + bonoEspecial;
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int;
    protected val numeroDos: Int;
    constructor(
        uno: Int,
        dos: Int
    ) {
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Inicializando");
    }
}

abstract class Numeros(
    protected val numeroUno: Int,
    protected val numeroDos: Int,
) {
    init {
        this.numeroUno;
        this.numeroDos;
        numeroUno;
        numeroDos;
        println("Inicializando");
    }
}

class Suma (
    unoParametro: Int,
    dosParametro: Int
) : Numeros(unoParametro, dosParametro) //Extendiendo y mandando parametros
{
    init {
        this.numeroUno;
        this.numeroDos;
    }
    constructor(
        uno: Int?,
        dos: Int
    ): this(
        if( uno == null) 0 else uno,
        dos
    )
    constructor(
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if( dos == null) 0 else dos,
    )

    constructor(
        uno: Int?,
        dos: Int?
    ): this(
        if( uno == null) 0 else uno,
        if( dos == null) 0 else dos,
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos;
        agregarHistorial(total);
        return total;
    }
    companion object { //Static
        val pi = 3.14;
        fun elevarAlCuadrado(num: Int): Int {
            return num * num;
        }
    }
    val historialSumas = ArrayList<Int>();
    fun agregarHistorial(valorNuevaSuma: Int) {
        historialSumas.add(valorNuevaSuma);
    }
}
