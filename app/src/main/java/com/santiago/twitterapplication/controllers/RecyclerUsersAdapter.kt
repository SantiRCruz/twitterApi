package com.santiago.twitterapplication.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.models.Constants
import com.santiago.twitterapplication.models.database.DBManager
import com.santiago.twitterapplication.models.users.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_users.view.*

class RecyclerUsersAdapter(val data : List<Data>) : RecyclerView.Adapter<RecyclerUsersAdapter.RecyclerUsersHolders>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerUsersHolders {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerUsersHolders(layoutInflater.inflate(R.layout.item_list_users,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerUsersHolders, position: Int) {
        holder.render(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class RecyclerUsersHolders(val view: View,val context : Context):RecyclerView.ViewHolder(view){
        fun render(data: Data){
            view.textViewItemEmail.text = data.email
            view.textViewItemFristName.text = data.first_name
            view.textViewItemLastName.text = data.last_name
            Picasso.get().load(data.avatar).into(view.imageViewItem)
            view.buttonItemEliminar.setOnClickListener {
                val dbManager = DBManager(context)
                dbManager.eliminar(Data(data.id,"","","",""))
                Navigation.findNavController(it).navigate(R.id.navigation_users)
            }
            view.buttonItemActualizar.setOnClickListener {
                Constants.LIST_ID = data.id
                Navigation.findNavController(it).navigate(R.id.navigation_actualizar)
            }
        }
    }
}