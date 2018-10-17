package com.dev.rio.footballapps.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavoriteMatchDatabaseOpenHelper (ctx: Context)
    : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: FavoriteMatchDatabaseOpenHelper? = null

        @Synchronized
    fun getInstance(ctx: Context): FavoriteMatchDatabaseOpenHelper{
            if (instance == null){
                instance = FavoriteMatchDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as FavoriteMatchDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
                FavoriteMatch.MATCH_DATE to TEXT,
                FavoriteMatch.MATCH_TIME to TEXT,

                //HOME
                FavoriteMatch.HOME_ID to TEXT,
                FavoriteMatch.HOME_TEAM to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,

                //AWAY
                FavoriteMatch.AWAY_ID to TEXT,
                FavoriteMatch.AWAY_TEAM to TEXT,
                FavoriteMatch.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.databaseFavoriteMatch: FavoriteMatchDatabaseOpenHelper
    get() = FavoriteMatchDatabaseOpenHelper.getInstance(applicationContext)