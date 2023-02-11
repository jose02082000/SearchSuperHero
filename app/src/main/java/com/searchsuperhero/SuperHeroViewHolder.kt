package com.searchsuperhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.searchsuperhero.databinding.RecyclerViewLayoutBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewLayoutBinding.bind(view)

    fun render(superHeroItemResponse: SuperHeroItemResponse) {
        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName
    }

}