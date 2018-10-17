package com.dev.rio.footballapps.views.main.teamsfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.models.team.Team
import com.squareup.picasso.Picasso

class TeamsAdapter (private val context: Context, private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val teamLogo: ImageView = view.findViewById(R.id.iv_team_logo)
        private val teamName: TextView = view.findViewById(R.id.tv_team_name)

        fun bindItem(teams: Team, listener: (Team) -> Unit) {
            Picasso.get().load(teams.teamLogo).into(teamLogo)
            teamName.text = teams.teamName
            itemView.setOnClickListener{ listener(teams) }
        }
    }

}