package com.example.sunmadinepal.fragment.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunmadinepal.R
import com.example.sunmadinepal.databinding.FragmentRecipesBinding
import com.example.sunmadinepal.fragment.nutrition.activity.RecipeForEnergyActivity
import com.example.sunmadinepal.model.setLocale
import java.util.*


class RecipesFragment : Fragment() {

    private lateinit var _binding: FragmentRecipesBinding
    val string = Locale.getDefault().language

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentRecipesBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.nutritionToolBar.toolbarActionTitle.text = getString(R.string.title_Recipes)
        goToDestinations()
        setLocale(this.requireContext(), string)
    }


    private fun goToDestinations() {

        _binding.apply {
            recipeEnergyCardView.setOnClickListener {

                val intent = Intent(requireContext(), RecipeForEnergyActivity::class.java).apply {
                    putExtra("tool_bar_title", getString(R.string.recipe_for_energy))
                }
                startActivity(intent)

            }

            /* recipesForPregnantWoman.setOnClickListener {
                 findNavController().navigate(R.id.action_navigation_recipes_to_recipes_Pregnant_Woman_Fragment) }

             recipesForMothers.setOnClickListener{
                 findNavController().navigate(R.id.action_navigation_recipes_to_recipes_Mothers_Fragment)
             }
             recipesForChildren.setOnClickListener{
                 findNavController().navigate(R.id.action_navigation_recipes_to_recipes_Children_Fragment)
             }*/

        }
    }
}
