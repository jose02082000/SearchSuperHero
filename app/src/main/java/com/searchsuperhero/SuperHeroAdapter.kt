package com.searchsuperhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(
    var superheroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit,
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    /**
     * como nosotros instanciamos nuestra lista vacía debemos de actualizarla cada vez que el api nos responda la peticion
     * entonces para esto creamos una funcion nueva en el adapter, a esta funcion le debemos pasar por parametro una
     * lista y especificar de que tipo es esta lista, en este caso utilizamos la misma porque se puede y porque es more easy
     * o tambien modificar el nombre para que se entienda mas facil. luego se notifica con el datasetchanged
     * y la funcion update la debemos de llamar en la activity cuando hayan cargado los valores de la peticion
     *
     * ahora que nosotros queremos que cuando le den tap a uno de los superheroes muestre la otra activity debemos utilizar una lambda esa funcion
     * labda nosotros la debemos de pasar por parametro en el adapter de la sgte manera: se crea una funcion dentro de los parametros esa funcion debe
     * tener por parametro el mismo tipo de dato que tiene la funcion que se crea en la activity y al final se coloca -> Unit
     *
     * como necesitamos que esta funcion la tengan cada uno de los items que nos devuelve la busqueda debemos de pasarle por parametro el
     * nombre de la funcion al iterador del recycler que es el onBindViewHolder cabe aclarar que el lugar correcto de colocar nuestra funcion es
     * luego de haberle pasado el objeto superHero con la posicion justo luego de eso se pone una coma y la funcion y se envía sin llaves
     * para que no lo ejecute solamente se llame, y luego en el viewHolder en la funcion que tenemos debemos de agg ese parametro nuevo igual a como
     * está declarado en la parte del adapter igual que arriba sin el private val y luego en la funcion de viewholder agg un listener
     *
     */

    fun updateList(list: List<SuperHeroItemResponse>) {
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
                false,
            ),
        )

        // return SuperHeroViewHolder(
        // LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout,parent, false))
    }

    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
        viewholder.render(superheroList[position], onItemSelected)

        // holder.render(listSuperHeroes[position])
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}
