package com.searchsuperhero

import com.google.gson.annotations.SerializedName

/**
 * aca creamos la data class que vamos a unir con el apiService en los parametros le vamos a pasar en forma de variable
 * asi como esta en el ejemplo con el cuidado de que EL NOMBRE DE LA VARIABLE DEBE SER IGUAL A LO QUE TENEMOS EN EL JSOM
 * en caso de que quieras conservar buenas practicas o de querer cambiar el nombre de la variable se utilizar el
 * @SerializedName("y aca el nombre de la variable") ya despues del val se podria colocar el nombre que tu quieras
 */
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String
)
