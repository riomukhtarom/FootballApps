package com.dev.rio.footballapps.views.detail.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.R.drawable.ic_add_to_favorites
import com.dev.rio.footballapps.R.drawable.ic_added_to_favorites
import com.dev.rio.footballapps.R.id.add_to_favorite
import com.dev.rio.footballapps.R.menu.detail_menu
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.databases.FavoriteTeam
import com.dev.rio.footballapps.databases.databaseFavoriteTeam
import com.dev.rio.footballapps.models.team.Team
import com.dev.rio.footballapps.presenters.DetailTeamPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var detailTeamPresenter: DetailTeamPresenter
    private lateinit var teams: Team
    private lateinit var teamId: String

    private lateinit var teamDescription: String

    val api = ApiRepository()
    val gson = Gson()

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teamId = intent.getStringExtra("id")

        view_pager_detail_teams.adapter = DetailTeamPagerAdapter(supportFragmentManager, "id", teamId)
        tab_layout_detail_teams.setupWithViewPager(view_pager_detail_teams)

        favoriteState()

        detailTeamPresenter = DetailTeamPresenter(this, api, gson)
        detailTeamPresenter.getTeamDetail(teamId)

    }

    override fun showLoading() {
        pb_detail_teams.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_teams.visibility = ProgressBar.INVISIBLE
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamLogo)
        Picasso.get().load(data[0].teamLogo).into(iv_team_detail_logo)
        tv_team_detail_name.text = data[0].teamName
        tv_team_detail_formed_year.text = data[0].teamFormedYear
        tv_team_detail_stadium.text = data[0].teamStadium
        teamDescription = data[0].teamDescription.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            databaseFavoriteTeam.use {
                insert(FavoriteTeam.TABLE_FAVORITE,
                        FavoriteTeam.TEAM_ID to teams.teamId,
                        FavoriteTeam.TEAM_NAME to teams.teamName,
                        FavoriteTeam.TEAM_BADGE to teams.teamLogo)
            }
            snackbar(coordinator_detail_team, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(coordinator_detail_team, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            databaseFavoriteTeam.use {
                delete(FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {teamId})",
                        "teamId" to teamId)
            }
            snackbar(coordinator_detail_team, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(coordinator_detail_team, e.localizedMessage).show()
        }
    }



    private fun favoriteState(){
        databaseFavoriteTeam.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {teamId})",
                            "teamId" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
