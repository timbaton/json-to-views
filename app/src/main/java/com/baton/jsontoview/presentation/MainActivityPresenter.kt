package com.baton.jsontoview.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.baton.jsontoview.data.ViewsRepository
import com.baton.jsontoview.utils.command.CommandFactory
import com.baton.jsontoview.utils.command.EditTextCommand
import com.baton.jsontoview.utils.command.SpinnerCommand
import com.baton.jsontoview.utils.viewBuilder.ViewCreator
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Project jsontoview
 * Package com.baton.jsontoview.ui
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
@InjectViewState
class MainActivityPresenter @Inject constructor(
    private var viewsRepository: ViewsRepository,
    private var viewCreator: ViewCreator
) : MvpPresenter<MainActivityView>() {

    private val compositeDisposable = CompositeDisposable()

    private var commandFactory: CommandFactory? = null

    private var error = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val spinnerOnAction = SpinnerCommand {
            onSpinnerChanged()
        }

        val editTextOnAction = EditTextCommand {
            switchToError()
        }

        commandFactory = CommandFactory().apply {
            spinnerCommand = spinnerOnAction
            editTextCommand = editTextOnAction
        }

        loadViews()
    }

    private fun loadViews() {
        compositeDisposable.add(
            viewsRepository.loadViews()
                .subscribe({
                    val views = viewCreator.getParsedViews(it.content, commandFactory!!)
                    viewState.createViews(views)
                }, {
                    viewState.showError()
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }

    private fun onSpinnerChanged() {
        Log.d("my tag", "spinner changed")
    }


    private fun showError(message: String) {
        viewState
    }


    private fun switchToError() {
        error = true
    }

    private fun switchToCorrect() {
        error = false
    }
}