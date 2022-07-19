package com.example.medlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedRVAdapter(var list: List<MedItems>,
                   val medItemClickInterface: MedItemClickInterface ) :
    RecyclerView.Adapter<MedRVAdapter.MedViewHolder>() {

    inner class MedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }


    interface MedItemClickInterface {
        fun onItemClick(medItems: MedItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.med_rv_item,parent,false)
           return MedViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text ="Rs. "+list.get(position).itemPrice.toString()
        val itemTotal : Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. "+itemTotal.toString()
        holder.deleteIV.setOnClickListener{
            medItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
         return list.size
    }


}