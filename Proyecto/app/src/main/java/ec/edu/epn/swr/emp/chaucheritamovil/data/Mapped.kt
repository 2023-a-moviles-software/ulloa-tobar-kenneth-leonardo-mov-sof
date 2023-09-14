package ec.edu.epn.swr.emp.chaucheritamovil.data

abstract class Mapped {
    abstract fun toMap(): Map<String, *>
    abstract fun toRefMap(): Map<String, *>
}