package com.example.sunmadinepal.ViewModel


import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunmadinepal.fragment.nutrition.activity.NutritionFoodItemsModel
import com.example.sunmadinepal.model.RecipesData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


class RecipesViewModel : ViewModel() {

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var _events = MutableLiveData<List<RecipesData>>()
    var nutritionFoodItemsEvents = MutableLiveData<List<NutritionFoodItemsModel>>()

    private val _text = MutableLiveData<String>().apply {
        value
    }
    val text: LiveData<String> = _text

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    var ItemImage = ""
    var itemName = ""
    var itemDescription = ""

    internal fun fetchEventRecipesForChildren(
        ItemName: String,
        ItemIngredients: String,
        jauloItemDirection : String,
        ItemName1: String,
        ItemDescription1: String,
        litoItemDirection : String,
        ItemName2: String,
        ItemDescription2: String,
        ItemName3: String,
        ItemDescription3: String,
        pumpkingDirection : String
    ) {
        firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {

                    ItemImage =document.data.getValue("app_jaulo").toString()
                    val ItemImage1 =document.data.getValue("app_litto").toString()
                    val ItemImage2 =document.data.getValue("NutritiousFlour").toString()
                    val ItemImage3 =document.data.getValue("PumpkinPudding").toString()

                    itemName = document.data.getValue(ItemName) as String
                    itemDescription = document.data.getValue(ItemIngredients).toString()
                    val itemDirection  = document.data.getValue(jauloItemDirection).toString()

                    val litoItemDirection = document.data.getValue(litoItemDirection).toString()
                    val pumpkingItemDirection = document.data.getValue(pumpkingDirection).toString()
                    val itemName1 = document.data.getValue(ItemName1) as String
                    val itemDescription1 = document.data.getValue(ItemDescription1).toString()
                    val itemName2 = document.data.getValue(ItemName2) as String
                    val itemDescription2 = document.data.getValue(ItemDescription2).toString()
                    val itemName3 = document.data.getValue(ItemName3) as String
                    val itemDescription3 = document.data.getValue(ItemDescription3).toString()


                    _events.value = listOf(
                        RecipesData(
                            ItemImage,
                            itemName,
                            itemDescription.replace("_b", "\n"),
                            itemDirection.replace("_b", "\n"),

                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage1,
                                itemName1,
                                itemDescription1.replace("_b", "\n"),
                                litoItemDirection.replace("_b", "\n"),

                            )
                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage2,
                                itemName2,
                                itemDescription2.replace("_b", "\n"),
                               ""
                            )
                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage3,
                                itemName3,
                                itemDescription3.replace("_b", "\n"),
                                pumpkingItemDirection.replace("_b", "\n"),
                            )
                        )
                    )
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }


    internal fun fetchEvent_0_6_Months(
        ItemName: String,
        ItemDescription: String,
        ItemName1: String,
        ItemDescription1: String,
        ItemName2: String,
        ItemDescription2: String
    ) {
        firestore.collection("Health").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {

                    val ItemImage = document.data.getValue("app_go_to_healthpost").toString()
                    val ItemImage1 = document.data.getValue("0to6BreastFeedingImageUrl").toString()
                    val ItemImage2 =
                        document.data.getValue("0to6BreastFeedingPositionImageUrl").toString()

                    val itemName = document.data.getValue(ItemName) as String
                    val itemDescription = document.data.getValue(ItemDescription).toString()
                    val itemName1 = document.data.getValue(ItemName1) as String
                    val itemDescription1 = document.data.getValue(ItemDescription1).toString()
                    val itemName2 = document.data.getValue(ItemName2) as String
                    val itemDescription2 = document.data.getValue(ItemDescription2).toString()


                    _events.value =
                        listOf(RecipesData(ItemImage, itemName, itemDescription, "")).plus(
                            listOf(RecipesData(ItemImage1, itemName1, itemDescription1, ""))
                        ).plus(listOf(RecipesData(ItemImage2, itemName2, itemDescription2, "")))
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

    }


    internal fun fetchEventRecipeForPregnant(
        ItemName: String,
        ItemDescription: String,
        ItemName1: String,
        ItemDescription1: String,
        ItemName2: String,
        ItemDescription2: String,
        ItemName3: String,
        ItemDescription3: String
    ) {

            firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {

                    ItemImage =document.data.getValue("app_jaulo").toString()
                    val ItemImage1 =document.data.getValue("ItemImage5").toString()
                    val ItemImage2 =document.data.getValue("NutritiousFlour").toString()
                    val ItemImage3 =document.data.getValue("PumpkinPudding").toString()

                    itemName = document.data.getValue(ItemName) as String
                    itemDescription = document.data.getValue(ItemDescription).toString()
                    val itemName1 = document.data.getValue(ItemName1) as String
                    val itemDescription1 = document.data.getValue(ItemDescription1).toString()
                    val itemName2 = document.data.getValue(ItemName2) as String
                    val itemDescription2 = document.data.getValue(ItemDescription2).toString()
                    val itemName3 = document.data.getValue(ItemName3) as String
                    val itemDescription3 = document.data.getValue(ItemDescription3).toString()


                    _events.value =
                        listOf(
                            RecipesData(
                                ItemImage,
                                itemName,
                                itemDescription,""
                            )
                        ).plus(
                            listOf(
                                RecipesData(
                                    ItemImage1,
                                    itemName1,
                                    itemDescription1,""
                                )
                            )
                        ).plus(
                            listOf(
                                RecipesData(
                                    ItemImage2,
                                    itemName2,
                                    itemDescription2,""
                                )
                            )
                        ).plus(
                            listOf(
                                RecipesData(
                                    ItemImage3,
                                    itemName3,
                                    itemDescription3,""
                                )
                            )
                        )
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }

    internal fun fetchEventRecipeForMothers(
        ItemName: String,
        ItemDescription: String,
        ItemName1: String,
        ItemDescription1: String,
        ItemName2: String,
        ItemDescription2: String,
        ItemName3: String,
        ItemDescription3: String
    ) {
        firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {

                    ItemImage =document.data.getValue("app_jaulo").toString()
                    val ItemImage1 =document.data.getValue("app_litto").toString()
                    val ItemImage2 =document.data.getValue("NutritiousFlour").toString()
                    val ItemImage3 =document.data.getValue("PumpkinPudding").toString()

                    itemName = document.data.getValue(ItemName) as String
                    itemDescription = document.data.getValue(ItemDescription).toString()
                    val itemName1 = document.data.getValue(ItemName1) as String
                    val itemDescription1 = document.data.getValue(ItemDescription1).toString()
                    val itemName2 = document.data.getValue(ItemName2) as String
                    val itemDescription2 = document.data.getValue(ItemDescription2).toString()
                    val itemName3 = document.data.getValue(ItemName3) as String
                    val itemDescription3 = document.data.getValue(ItemDescription3).toString()


                    _events.value = listOf(
                        RecipesData(
                            ItemImage,
                            itemName,
                            itemDescription,""
                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage1,
                                itemName1,
                                itemDescription1,""
                            )
                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage2,
                                itemName2,
                                itemDescription2,""
                            )
                        )
                    ).plus(
                        listOf(
                            RecipesData(
                                ItemImage3,
                                itemName3,
                                itemDescription3,""
                            )
                        )
                    )
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }


    internal fun fetchNutritionFoodItems(
        ItemName: String, itemImageUrl : String
    ) {
        firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val foodItemEnergyContent = document.data.getValue(ItemName) as String
                    val foodItemEnergyImage = document.data.getValue(itemImageUrl) as String
                    Log.d("TAG ", foodItemEnergyContent)
                    nutritionFoodItemsEvents.value =
                        listOf(NutritionFoodItemsModel(foodItemEnergyContent.replace("_b", "\n"),foodItemEnergyImage))
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }


    internal fun fetchNutritionFoodProtection(
        ItemName: String, itemImageUrl : String
    ) {
        firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val foodItemEnergyContent = document.data.getValue(ItemName) as String
                    val foodItemEnergyImage = document.data.getValue(itemImageUrl) as String
                    Log.d("TAG ", foodItemEnergyContent)
                    nutritionFoodItemsEvents.value =
                        listOf(NutritionFoodItemsModel(foodItemEnergyContent.replace("_b", "\n"),foodItemEnergyImage))
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }

    internal fun fetchNutritionForStrength(
        ItemName: String, itemImageUrl : String
    ) {
        firestore.collection("Recipes").get().addOnCompleteListener {
            if (it.isCanceled) {
                Log.e("Error", " Error in database")
            }
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val foodItemEnergyContent = document.data.getValue(ItemName) as String
                    val foodItemEnergyImage = document.data.getValue(itemImageUrl) as String
                    Log.d("TAG ", foodItemEnergyContent)
                    nutritionFoodItemsEvents.value =
                        listOf(NutritionFoodItemsModel(foodItemEnergyContent.replace("_b", "\n"),foodItemEnergyImage))
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    }




    internal var events: MutableLiveData<List<RecipesData>>
        get() {
            return _events
        }
        set(value) {
            _events = value
        }

}


