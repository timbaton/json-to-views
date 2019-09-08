package com.baton.jsontoview.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.baton.jsontoview.R
import com.baton.jsontoview.di.Scopes
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Toothpick

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(MainActivityPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showError() {
        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show()
    }

    override fun createViews(views: List<View>) {
        views.forEach {
            llRoot.addView(it)
        }
    }
}
