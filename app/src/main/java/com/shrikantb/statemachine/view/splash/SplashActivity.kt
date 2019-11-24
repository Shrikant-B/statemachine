package com.shrikantb.statemachine.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.shrikantb.statemachine.R
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.MovieList
import com.shrikantb.statemachine.view.movies.MoviesActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.stateObserver.observe(this, Observer { state ->
            when (state) {
                is MovieList -> {
                    startActivity(Intent(this, MoviesActivity::class.java))
                    finish()
                }
            }
        })

        Thread(Runnable {
            Thread.sleep(2500L)
            runOnUiThread { splashViewModel.changeState(Action.ScreenNavigation()) }
        }).start()
    }
}