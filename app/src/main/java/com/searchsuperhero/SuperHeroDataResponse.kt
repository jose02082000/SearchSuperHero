package com.searchsuperhero

import com.google.gson.annotations.SerializedName

/**
 * aca creamos la data class que vamos a unir con el apiService en los parametros le vamos a pasar en forma de variable
 * asi como esta en el ejemplo con el cuidado de que EL NOMBRE DE LA VARIABLE DEBE SER IGUAL A LO QUE TENEMOS EN EL JSOM
 * en caso de que quieras conservar buenas practicas o de querer cambiar el nombre de la variable se utilizar el
 * @SerializedName("y aca el nombre de la variable") ya despues del val se podria colocar el nombre que tu quieras
 *
 * para poder ver los items que nos da la respuesta del json creamos en este caso una nueva data class donde en le decimos
 * que es sera un itemresponse y en su interior los items que vamos a recuperar a esto nosotros le decimos que es una
 * lista que se llama SuperHeroItemResponse y para cada uno de los datos que queramos recuperar debemos mirar en que
 * jerarquía estan para crear o no una nueva data class en este caso que se agg la imagen el json nos muestra de que es
 * otra jerarquia de este modo debemos de crear otro dataclass y en el añadir los datos de la imagen, esto es para cada
 * uno de los elementos que queramos recuperar, de este modo como nosotros tenemos una lista de items recuperados
 * vamos a agregar cada uno de los items que recuperamos a esta lista se crea una @SerializedName dentro de nuestra lista
 * y se hace el proceso como se realiza con la imagen
 */

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>,
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("image") val superHeroImage: SuperHeroImageResponse,
)

data class SuperHeroImageResponse(@SerializedName("url") val url: String)
