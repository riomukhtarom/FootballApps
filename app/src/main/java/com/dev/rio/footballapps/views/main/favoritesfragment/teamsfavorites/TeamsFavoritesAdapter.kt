package com.dev.rio.footballapps.views.main.favoritesfragment.teamsfavorites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.databases.FavoriteTeam
import com.squareup.picasso.Picasso

class TeamsFavoritesAdapter (private val context: Context, private val favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<TeamsFavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position], listener)
    }

    override fun getItemCount(): Int {
        return favoriteTeam.size
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val teamLogo: ImageView = view.findViewById(R.id.iv_team_logo)
        private val teamName: TextView = view.findViewById(R.id.tv_team_name)

        fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            Picasso.get().load(favoriteTeam.teamLogo).into(teamLogo)
            teamName.text = favoriteTeam.teamName
            itemView.setOnClickListener{ listener(favoriteTeam) }
        }
    }
}


