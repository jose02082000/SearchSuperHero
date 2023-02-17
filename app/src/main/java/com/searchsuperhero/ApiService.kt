package com.searchsuperhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /**
     * aca creamos el archivo nuevo de apiService para hacer la peticion de a nuestra api
     * primero se coloca el tipo de peticion que se va a realizar en este caso @GET siempre debe ser en mayus
     * luego en los parentesis se coloca el ENDPOINT de la peticion que se va a realizar siempre entre comillas dobles
     * en este caso la ultima parte se coloca el llaves porque es como si fueramos a trabajar con un parametro
     * entonces en esa busqueda lo que va a cambiar el justo esa palabra "name" y luego se hace un proceso en la funcion
     * de abajo. en este caso cuando se trabja con las corrutinas se debe colocar suspend fun para que trabaje en otro hilo
     * al interior de los parentesis de la funcion se coloca @Path y al interior de este "name" para que kotlin entienda que
     * esa es la palabra que se va a modificar, luego de esto se entiende de que lo que vamos a recibir es una lista entonces
     * debemos de crear una dataclass con la cual nosotros vamos a decirle a la api que datos son los que vamos a tomar
     * de esa peticion el nombre que le colocamos a la data class lo pasamos entre <>
     * tener cuidado con los imports de retrofit no estan dando
     *
     */

    @GET("5787537781343419/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    /**
     * para nuestra segunda peticion hacemos todo casi tal cual como esta arriba obiamente como ya no vamos a
     *necesitar la lista como respuesta ahora nosotros creamos una nueva data class y en ella implementamos cada uno
     * de los atributos que vamos a mostrar en nuestra segunda activity
     *
     */
    @GET("5787537781343419/{id}")
    suspend fun getSuperHeroDetail(@Path("id") ideSuperHero: String): Response<SuperHeroDetailResponse>
}
