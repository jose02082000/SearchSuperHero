package com.searchsuperhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter( var superheroList: List<SuperHeroItemResponse> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    /**
     * como nosotros instanciamos nuestra lista vacía debemos de actualizarla cada vez que el api nos responda la peticion
     * entonces para esto creamos una funcion nueva en el adapter, a esta funcion le debemos pasar por parametro una
     * lista y especificar de que tipo es esta lista, en este caso utilizamos la misma porque se puede y porque es more easy
     * o tambien modificar el nombre para que se entienda mas facil. luego se notifica con el datasetchanged
     * y la funcion update la debemos de llamar en la activity cuando hayan cargado los valores de la peticion
     *
     */

    fun updateList(list: List<SuperHeroItemResponse>){
        superheroList = list
        notifyDataSetChanged()
    }

    /**
     *   fun updateList(listSuperHeroes: List<SuperHeroItemResponse>){
            this.listSuperHeroes = listSuperHeroes
        } 💩
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return SuperHeroViewHolder(
            layoutInflater.inflate(
                R.layout.recycler_view_layout,
                parent,
                false
            )
        )

    //return SuperHeroViewHolder(
    // LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout,parent, false))

    }

    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
        viewholder.render(superheroList[position])

        // holder.render(listSuperHeroes[position])
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }


}