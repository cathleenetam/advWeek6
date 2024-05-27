package com.duodinamika.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duodinamika.advweek4.R
import com.duodinamika.advweek4.model.Motogp

class MotogpListAdapter (val motogpList:ArrayList<Motogp>) : RecyclerView.Adapter<MotogpListAdapter.MotogpViewHolder>(){
    class MotogpViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotogpViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.motogp_list_item, parent, false)
        return MotogpViewHolder(view)
    }

    override fun getItemCount(): Int {
        return motogpList.size
    }

    override fun onBindViewHolder(holder: MotogpViewHolder, position: Int) {
        val txtIdM = holder.itemView.findViewById<TextView>(R.id.txtIdMotogp)
        val txtNameM = holder.itemView.findViewById<TextView>(R.id.txtNameMotogp)
        val txtManufactureM = holder.itemView.findViewById<TextView>(R.id.txtManufactureMotogp)
        val txtModelM = holder.itemView.findViewById<TextView>(R.id.txtModelMotogp)
        val txtTeamM = holder.itemView.findViewById<TextView>(R.id.txtTeamMotogp)

        var id = motogpList[position].id
        var name = motogpList[position].rider
        var manuf = motogpList[position].bike?.manufacturer
        var model = motogpList[position].bike?.model
        var team = motogpList[position].team

        txtIdM.text = "$id"
        txtNameM.text = "Name: $name"
        txtTeamM.text = "Team: $team"
        txtManufactureM.text = "Bike: $manuf"
        txtModelM.text = "$model"
    }

    fun updateMotogpList(newMotogpList: ArrayList<Motogp>){
        motogpList.clear()
        motogpList.addAll(newMotogpList)
        notifyDataSetChanged()
    }
}