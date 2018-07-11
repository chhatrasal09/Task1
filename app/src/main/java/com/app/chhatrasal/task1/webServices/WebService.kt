package com.app.chhatrasal.task1.webServices

import com.app.chhatrasal.task1.models.ServerResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface WebService {

    @GET("/tutorial/jsonparsetutorial.txt")
    fun getFlagList() : Observable<ServerResponse>
}