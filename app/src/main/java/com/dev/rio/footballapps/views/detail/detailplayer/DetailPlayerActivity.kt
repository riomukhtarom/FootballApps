package com.dev.rio.footballapps.views.detail.detailplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ProgressBar
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.models.player.Player
import com.dev.rio.footballapps.presenters.DetailPlayerPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView{

    private lateinit var detailPlayerPresenter: DetailPlayerPresenter
    private lateinit var playerName: String
    private lateinit var playerId: String

    val api = ApiRepository()
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        playerId = intent.getStringExtra("id")

        detailPlayerPresenter = DetailPlayerPresenter(this, api, gson)
        detailPlayerPresenter.getPlayerDetail(playerId)

    }

    override fun showLoading() {
        pb_detail_player.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_player.visibility = ProgressBar.INVISIBLE
    }

    override fun showPlayerDetail(data: List<Player>) {
        if(data[0].playerFanArt==null){
            Picasso.get().load(data[0].playerPhotoDetail).into(iv_detail_player_photo)
        } else {
            Picasso.get().load(data[0].playerFanArt).into(iv_detail_player_photo)
        }

        if(data[0].playerWeight.equals("")){
            tv_weight_value.text = "-"
        } else {
            tv_weight_value.text = data[0].playerWeight
        }

        if(data[0].playerHeight.equals("")){
            tv_height_value.text = "-"
        } else {
            tv_height_value.text = data[0].playerHeight
        }

        tv_detail_player_position.text = data[0].playerPosition
        tv_detail_player_description.text = data[0].playerDescription
        playerName = data[0].playerName.toString()

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
