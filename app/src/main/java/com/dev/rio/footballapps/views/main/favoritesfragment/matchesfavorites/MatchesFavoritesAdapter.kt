package com.dev.rio.footballapps.views.main.favoritesfragment.matchesfavorites

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.databases.FavoriteMatch

class MatchesFavoritesAdapter (private val context: Context, private val favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<MatchesFavoritesAdapter.MatchViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_matches, parent, false))
    }

    override fun getItemCount(): Int {
        return favoriteMatch.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(favoriteMatch[position], listener)
    }


    class MatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate = view.findViewById<TextView>(R.id.tv_date)
        private val teamHome = view.findViewById<TextView>(R.id.tv_team1)
        private val teamAway = view.findViewById<TextView>(R.id.tv_team2)
        private val scoreHome = view.findViewById<TextView>(R.id.tv_score1)
        private val scoreAway = view.findViewById<TextView>(R.id.tv_score2)
        private val matchTime = view.findViewById<TextView>(R.id.tv_time)

        @SuppressLint("SimpleDateFormat")
        fun bindMatchItem (favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit){

            //var dateFormat = SimpleDateFormat("EEE, dd MMM yyyy").format((favoriteMatch.matchDate))
            //val timeStart = SimpleDateFormat("HH:mm").parse(favoriteMatch.matchTime)

            //val dateParse = SimpleDateFormat("HH:mm:ssZ").parse(favoriteMatch.matchTime)
            //val timeFormat = SimpleDateFormat("HH:mm").format(favoriteMatch.matchTime)

            /*if(dateParse.hours<=8 && timeStart.hours>=15) {
                val calendar = Calendar.getInstance()
                calendar.time = SimpleDateFormat("EEE, dd MMM yyyy").parse(dateFormat)
                calendar.add(Calendar.DATE,1)
                dateFormat = SimpleDateFormat("EEE, dd MMM yyyy").format(calendar.time)
            }*/

            matchDate.text = favoriteMatch.matchDate
            matchTime.text = favoriteMatch.matchTime
            teamHome.text = favoriteMatch.homeTeam
            teamAway.text = favoriteMatch.awayTeam
            if(favoriteMatch.homeScore == "null"){
                scoreHome.text = ""
                scoreAway.text = ""
            }else{
                scoreHome.text = favoriteMatch.homeScore
                scoreAway.text = favoriteMatch.awayScore
            }
            itemView.setOnClickListener { listener(favoriteMatch) }
        }
    }
}