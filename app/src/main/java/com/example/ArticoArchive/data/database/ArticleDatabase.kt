package com.example.ArticoArchive.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ArticoArchive.data.dao.ArticleDao
import com.example.ArticoArchive.data.dataclass.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object{
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(context: Context) : ArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}