package com.baton.jsontoview.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.baton.jsontoview.data.ViewsRepository
import com.baton.jsontoview.viewBuilders.factories.ViewPropertiesFactory
import com.baton.jsontoview.viewBuilders.editText.EditTextViewProperty
import com.baton.jsontoview.viewBuilders.spinner.SpinnerViewProperty
import com.baton.jsontoview.viewBuilders.ViewsAdapter
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
    private var viewsAdapter: ViewsAdapter,
    private var viewPropertiesFactory: ViewPropertiesFactory
) : MvpPresenter<MainActivityView>() {

    private val compositeDisposable = CompositeDisposable()

    private var error = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val spinnerViewProperty = SpinnerViewProperty {
            onSpinnerChanged()
        }

        val editTextViewProperty = EditTextViewProperty {
            switchToError()
        }

        viewPropertiesFactory.apply {
            spinnerProperty = spinnerViewProperty
            editTextProperty = editTextViewProperty
        }

        loadViews()
    }

    private fun loadViews() {
        compositeDisposable.add(
            viewsRepository.loadViews()
                .subscribe({
                    val views = viewsAdapter.getParsedViews(it.content)
                    viewState.showViews(views)
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
        viewsAdapter.onSpinnerChanged()
    }

    private fun switchToError() {
        error = true
    }

    private fun switchToCorrect() {
        error = false
    }
}