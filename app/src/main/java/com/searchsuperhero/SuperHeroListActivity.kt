package com.searchsuperhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.searchsuperhero.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    /**
     * en este lugar inicializamos el adapter para luego llamarlo en ek iniUi
     */
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUi()
    }

    private fun initUi() {
        /**
         * en esta funcion lo que realizamos es que colocamos el escuchador del searchview, este escuchador es especial
         * ya que es diferente a los demas, entre los parentesis le decimos que es un objeto de tipo searchview y colocamos
         * el "." para desplegar los atributos del objeto seleccionamos el que se necesita en este caso que es el onQueryTextListener
         * se agg el import y luego de esto se abren llaves justo despues del onquery se implementan los dos metodos desde la bombilla
         * en la funcion de abajo en este caso no se toca y se retorna un false y en la primer funcion estamos creando una funcion
         * externa en donde vamos a buscar por el nombre y le enviamos por parametro "query" ya que este es el parametro con el que
         * esta trabajando la funcion que implementamos en este caso para que al crear la funcion externa no sea un string nulable
         * le agregamos el atributo ".orEmpty()" que significa o vacío y retornamos un false
         */

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        /**
         * como ya inicializamos arriba el adapter aca lo llamamos y le decimos que es igual a nuestra clase adapter
         * el setHasFixed es porque luego de la busqueda el tamaño del recycler es fijo entoces se optimiza con esto
         * no se que hace especificamente pero es necesario
         * y conectamos ahora el layout del RV con el adapter
         */
        adapter = SuperHeroAdapter()
        binding.rvSuperHeroes.setHasFixedSize(true)
        binding.rvSuperHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroes.adapter = adapter

    }

    private fun searchByName(query: String) {
        /**
         * cuando creamos la progressBar debemos hacer que funcione en la funcion de busqueda que justo es esta
         * hay que tener en cuenta y es muy importante que solo se puede crear vistas en el hilo principal
         * y como estamos trabajando con corrutinas debemos de hacerlo de una manera especial
         * se coloca la funcion runOnUiThread y entre las llaves la linea del progressbar
         */

        /**
         * aca en esta funcion estamos creando la corrutina para que pueda trabajar con mas hilos se declara courutineScoup
         * (Dispatchers.IO) el punto io es para cuando se usa el hilo secundario para peticiones de red o guardar en bases de datos
         * o procesos muy largos y punto launch para que se lance. al interior de estas llaves creamos una variable
         * que es igual a retrofit.create(ApiService::class.java).getSuperherores(query) que nos trae la respuesta a la
         * peticion de la api
         * se crea un if para verificar que la si la peticion es correcta lo veamos con el log y al interior de esta
         * se crea una variabe y decimos que es igual a el nombre de la variable que nos trae la peticion .body()
         * aca creamos de momento otro if para mirar con el log que si funcione, le decimos que si la respuesta de la api
         * es diferente de null el adapter se debe actualizar con la respuesta.y el nombre de la lista que actualiza
         */

        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful) {
                Log.i("jose", "funciona")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("jose", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible = false
                    }
                }

            } else {
                Log.i("jose", "No funciona")
                runOnUiThread {
                    binding.progressBar.isVisible = false
                }
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        /**
         * aca estamos creando el objeto de tipo retrofit lo que se hace es sencillo se crea la funcion y se dice que es de tipo
         * Retrofit y se importa luego le decimos que Retrofit.Builder().BaseUrl y aca colocamos la base de la api
         * MUY IMPORTANTE NO OLVIDAR EL "/" AL FINAL PARA QUE NO LLORES LAGRIMAS DE SANGRE
         * luego .addConverterFactory(GsonConverterFactory.Create()).build y listo no es nada del otro mundo y en este caso que aun
         * no estamos usando arquitectura lo delaramos en la parte superior de la activity
         */
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
