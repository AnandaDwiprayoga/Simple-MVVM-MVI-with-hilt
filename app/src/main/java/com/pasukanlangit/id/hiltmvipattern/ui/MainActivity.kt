package com.pasukanlangit.id.hiltmvipattern.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.pasukanlangit.id.hiltmvipattern.R
import com.pasukanlangit.id.hiltmvipattern.model.Blog
import com.pasukanlangit.id.hiltmvipattern.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        suscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun suscribeObservers(){
        viewModel.dataState.observe(this, { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        text.text = message ?: "Unkown Error"
    }
    private fun displayProgressBar(isDisplayed: Boolean){
        loading.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        val sb= StringBuffer()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        text.text = sb
    }
}