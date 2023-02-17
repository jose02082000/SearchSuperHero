package com.searchsuperhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.searchsuperhero.databinding.RecyclerViewLayoutBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewLayoutBinding.bind(view)

    fun render(superHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName
        /**
         * aca para implementar picasso se hace asi tal cual copy past obviamnete con los atributos correspondientes
         * lo que hay aca es que se implementa la libreria y se carga una url en algun lugar que nosotros le digamos
         */
        Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.ivSuperHero)
        /**
         * aca seguimos con la funcion lambda en este punto debemos de agg el listener a cada card entonces utilizamos el binding.root.setOnclic
         * y al interior de las llaves del listener vamos a enviar lo que queremos que se llame que en este caso es la funcion lambda y un id para
         * que este sepa a que superheroe corresponde esto lo hacemos llamando al objeto superHeroItemResponse.superHeroId ya que estamos llamando
         * al objeto que necesitamos para recuperar el id que viene siendo un atributo de este objeto ahpra debemos de enganchar esta lambda
         * con la funcion del activity esto se hace desde la activity
         */
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superHeroId) }
    }
}
