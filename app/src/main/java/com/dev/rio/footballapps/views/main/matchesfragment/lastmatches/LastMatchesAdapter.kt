package com.dev.rio.footballapps.views.main.matchesfragment.lastmatches

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.models.match.Match
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class LastMatchesAdapter (private val context: Context, private val matchs: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<LastMatchesAdapter.MatchViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_matches, parent, false))
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(matchs[position], listener)
    }


    class MatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate = view.findViewById<TextView>(R.id.tv_date)
        private val teamHome = view.findViewById<TextView>(R.id.tv_team1)
        private val teamAway = view.findViewById<TextView>(R.id.tv_team2)
        private val scoreHome = view.findViewById<TextView>(R.id.tv_score1)
        private val scoreAway = view.findViewById<TextView>(R.id.tv_score2)
        private val matchTime = view.findViewById<TextView>(R.id.tv_time)

        @SuppressLint("SimpleDateFormat")
        fun bindMatchItem (matchs: Match, listener: (Match) -> Unit){

            var dateFormat = SimpleDateFormat("EEE, dd MMM yyyy").format(matchs.matchDate)
            val timeStart = SimpleDateFormat("HH:mm").parse(matchs.matchTime)

            val dateParse = SimpleDateFormat("HH:mm:ssZ").parse(matchs.matchTime)
            val timeFormat = SimpleDateFormat("HH:mm").format(dateParse)

            if(dateParse.hours<=8 && timeStart.hours>=15) {
                val calendar = Calendar.getInstance()
                calendar.time = SimpleDateFormat("EEE, dd MMM yyyy").parse(dateFormat)
                calendar.add(Calendar.DATE,1)
                dateFormat = SimpleDateFormat("EEE, dd MMM yyyy").format(calendar.time)
            }

            matchDate.text = dateFormat
            matchTime.text = timeFormat
            teamHome.text = matchs.homeTeam
            teamAway.text = matchs.awayTeam
            scoreHome.text = matchs.homeScore
            scoreAway.text = matchs.awayScore
            itemView.setOnClickListener { listener(matchs) }
        }
    }
}