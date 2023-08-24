package com.example.movilessoftware2023a

class ICities(
    var name: String?,
    var state: String?,
    var country: String?,
    var capital: Boolean?,
    var population: Number?,
    var regiones: List<String>?
) {
    override fun toString(): String {
        return "$name - $country"
    }

}
