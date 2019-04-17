package com.delicious.mvvm.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.delicious.mvvm.R
import com.delicious.mvvm.data.model.FeatureModel
import java.util.*

class FeatureAdapter(
  features: List<FeatureModel>?) : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

  private var featuresList = ArrayList<FeatureModel>()

  init {
    this.featuresList = features as ArrayList<FeatureModel>
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feature_list_item,
        parent, false)
    return FeatureViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return featuresList.size
  }

  override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
    val featureModel = featuresList[position]
    holder.featureListItem(featureModel)
  }

  fun addFeatures(features: List<FeatureModel>) {
    val initPosition = featuresList.size
    featuresList.addAll(features)
    notifyItemRangeInserted(initPosition, featuresList.size)
  }

  class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var featureImage = itemView.findViewById<TextView>(R.id.feature_image)!!
    private var featureName = itemView.findViewById<TextView>(R.id.feature_name)!!
    private var featureRestaurant = itemView.findViewById<TextView>(R.id.feature_restaurant_name)!!
    private var featureOrder = itemView.findViewById<TextView>(R.id.order)!!

    fun featureListItem(featureModel: FeatureModel) {
      //TODO:GLIDE v4 Kotlin integration
      //TODO:PROCESSING ORDER BUTTON CLICK
//      GlideApp.with(context)
//        .load(featureModel.meal.image_url)
//        .fitCenter()
//        .into(featureImage)
      featureName.text = featureModel.name
      featureRestaurant.text = featureModel.restaurant.name
    }
  }
}