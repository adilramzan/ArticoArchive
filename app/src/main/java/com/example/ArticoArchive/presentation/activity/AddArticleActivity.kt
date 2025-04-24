package com.example.ArticoArchive.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ArticoArchive.R
import com.example.ArticoArchive.data.dataclass.Article
import com.example.ArticoArchive.databinding.ActivityAddArticleBinding
import com.example.ArticoArchive.viewModel.ArticleViewModel

class AddArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddArticleBinding
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_article)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveArticleButton.setOnClickListener {
            val title = binding.articleTitle.text.toString()
            val content = binding.articleContent.text.toString()
            val author = binding.articleAuthor.text.toString()
            val timeStamp = System.currentTimeMillis()
            val article = Article(
                title = title,
                content = content,
                timeStamp = timeStamp,
                author = author
            )
            articleViewModel.insert(article)
            finish()
        }
    }
}
