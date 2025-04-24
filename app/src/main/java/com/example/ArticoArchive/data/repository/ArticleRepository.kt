package com.example.ArticoArchive.data.repository

import com.example.ArticoArchive.data.dao.ArticleDao
import com.example.ArticoArchive.data.dataclass.Article
import kotlinx.coroutines.flow.Flow

class ArticleRepository(private val articleDao: ArticleDao) {

    val allArticles: Flow<List<Article>> = articleDao.getAllArticles()

    suspend fun insert(article: Article) {
        articleDao.insertArticle(article)
    }

    suspend fun delete(article: Article) {
        articleDao.deleteArticle(article)
    }

    suspend fun countArticles(): Int {
        return articleDao.countArticles()
    }
}