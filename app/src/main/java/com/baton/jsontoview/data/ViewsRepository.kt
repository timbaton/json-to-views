package com.baton.jsontoview.data

import com.baton.jsontoview.data.entity.ViewResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Project jsontoview
 * Package com.baton.jsontoview
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
class ViewsRepository @Inject constructor(
    private var viewApi: ViewApi
){
    fun loadViews(): Single<ViewResponse> {
        return viewApi.loadViews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}