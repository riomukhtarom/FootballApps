package com.dev.rio.footballapps.views.detail.detailteam.playerlistfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.models.player.Player
import com.squareup.picasso.Picasso

class PlayerListAdapter (private val context: Context, private val player: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_players, parent, false))
    }

    override fun getItemCount(): Int {
        return player.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val playerImage: ImageView = view.findViewById(R.id.iv_player_photo)
        private val playerName: TextView = view.findViewById(R.id.tv_player_name)
        private val playerPosition: TextView = view.findViewById(R.id.tv_player_position)

        fun bindItem(player: Player, listener: (Player) -> Unit) {
            if (player.playerPhotoCut!=null){
                Picasso.get().load(player.playerPhotoCut).into(playerImage)
            } else {
                Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/9/97/Anonim.png").into(playerImage)
            }
            playerName.text = player.playerName
            playerPosition.text = player.playerPosition
            itemView.setOnClickListener{ listener(player) }
        }
    }

}