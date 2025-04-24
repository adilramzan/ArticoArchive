package com.example.ArticoArchive.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ArticoArchive.data.database.ArticleDatabase
import com.example.ArticoArchive.data.dataclass.Article
import com.example.ArticoArchive.data.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ArticleRepository

    private val _allArticles = MutableStateFlow<List<Article>>(emptyList())
    val allArticles: StateFlow<List<Article>> = _allArticles

    private val _articleCount = MutableStateFlow(0)
    val articleCount: StateFlow<Int> = _articleCount

    init {
        val dao = ArticleDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(dao)

        viewModelScope.launch {
            repository.allArticles.collect { articles ->
                _allArticles.value = articles
                _articleCount.value = articles.size
            }
        }
    }

    fun insert(article: Article) = viewModelScope.launch {
        repository.insert(article)
    }

    fun delete(article: Article) = viewModelScope.launch {
        repository.delete(article)
    }
}