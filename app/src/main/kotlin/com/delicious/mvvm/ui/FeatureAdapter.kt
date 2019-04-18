package com.delicious.mvvm.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.delicious.mvvm.GlideApp
import com.delicious.mvvm.R
import com.delicious.mvvm.data.model.FeatureModel
import java.util.*

class FeatureAdapter(
  features: List<FeatureModel>?) : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

  private var featuresList = ArrayList<FeatureModel>()

  fun getFeatures(): ArrayList<FeatureModel> {
    return featuresList
  }

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

    private var featureImage = itemView.findViewById<ImageView>(R.id.feature_image)!!
    private var featureName = itemView.findViewById<TextView>(R.id.feature_name)!!
    private var featureRestaurant = itemView.findViewById<TextView>(R.id.feature_restaurant_name)!!
    private var featureOrder = itemView.findViewById<Button>(R.id.order)!!

    fun featureListItem(featureModel: FeatureModel) {
      GlideApp.with(featureImage)
        .load(featureModel.meal.image_url)
        .fitCenter()
        .into(featureImage)
      featureName.text = featureModel.meal.name
      featureRestaurant.text = featureModel.restaurant.name
      featureOrder.setOnClickListener(View.OnClickListener {
         if (featureModel.ordered!!) {
           Toast.makeText(featureImage.context, "Sorry, but you have one already.\nThis is some kind of error message", Toast.LENGTH_SHORT).show()
         } else {
           featureModel.ordered = true
           Toast.makeText(featureImage.context, "Success! Order with itemId\n" + featureModel.id + "\nhave already added into your basket ;)", Toast.LENGTH_SHORT).show()
           //TODO: STORE ID SOMEWHERE or NOT?
         }
      })
    }
  }
}