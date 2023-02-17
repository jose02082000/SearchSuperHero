package com.searchsuperhero

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.searchsuperhero.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         * aca estamos recuperando el intent que enviamos desde la otra activity este dato lo vamos a guardar en una variable
         * luego tocamos la bombilla para ver el tipo de dato que es y como este nos marca que puede ser nulo nosotros lo
         * modificamos quitando el ? al string y de este modo al final le decimos que si llega nulo lo muestre vacío
         * despues creamos un metodo que este va a recuperar la informacion que nosotros le indiquemos tambien esta funcion
         * debe de trabajar por parametro obligatoriamente con la id que enviamos en el intent
         */
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    /**
     * en esta funcion nosotros debemos de hacer una peticion a internet entonces como ya lo hicimos en la activity principal
     * podemos copiar codigo o hacerlo nuevamente
     * aca recordar que cuando se llama al getSuperHeroDetail() este trabaja con una id que es la que tiene por parametro
     * el getSuperHeroInformation entonces se lo colocamos a getSuperHeroDetail(id) y la linea del getRetrofit la metemos
     * en una variable. luego de esto una manera de verificar que nuestra respuesta no es nula es mediante un if y dentro de este
     * if nosotros le decimos por medio del runOnUiThread que pinte en la vista principal, al interior de esta nosotros
     * creamos una variable que trabaja por parametro con el superHeroDetail.body()
     *
     */
    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)
            if (superHeroDetail != null) {
                runOnUiThread { createUi(superHeroDetail.body()!!) }
            }
        }
    }

    /**
     * en esta funcion cambiamos en los parametros el body por un superHero de tipo SuperHeroDetailResponse y guardamos la imagen con picasso en el IV
     * y tambien le asignamos el valor al TV del superHero
     */
    private fun createUi(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerstats)
        preparePowerStats(superHero.powerstats)
        binding.tvSuperHeroRealName.text = superHero.biography.fullName
        binding.tvSuperHeroPublisher.text = superHero.biography.piblisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.combat, powerstats.combat)
        updateHeight(binding.durability, powerstats.durability)
        updateHeight(binding.intelligence, powerstats.intelligence)
        updateHeight(binding.speed, powerstats.speed)
        updateHeight(binding.strength, powerstats.strength)
        updateHeight(binding.power, powerstats.power)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pixelToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pixelToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    private fun preparePowerStats(powerstats: PowerStatsResponse) {
        binding.tvintelligence.text = powerstats.intelligence
        binding.tvspeed.text = powerstats.speed
        binding.tvstrength.text = powerstats.strength
        binding.tvdurability.text = powerstats.durability
        binding.tvpower.text = powerstats.power
        binding.tvcombat.text = powerstats.combat
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
