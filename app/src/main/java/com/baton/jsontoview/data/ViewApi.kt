package com.baton.jsontoview.data

import com.baton.jsontoview.data.entity.ViewResponse
import io.reactivex.Single
import retrofit2.http.GET



/**
 * Project jsontoview
 * Package com.baton.jsontoview.data
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-09-07
 *
 */
interface ViewApi {

    @GET("mobile/form/form.json")
    fun loadViews(): Single<ViewResponse>
}