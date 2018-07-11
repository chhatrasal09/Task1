package com.app.chhatrasal.task1.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.app.chhatrasal.task1.R
import com.app.chhatrasal.task1.adapters.ImageRecyclerAdapter
import com.app.chhatrasal.task1.models.ServerResponse
import com.app.chhatrasal.task1.webServices.WebService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var retrofit: Retrofit
    private val url: String = "http://www.androidbegin.com/"
    private lateinit var imageRecyclerAdapter: ImageRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        fetchData()
    }

    private fun initViews() {
        retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        recyclerView = findViewById(R.id.flag_list)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)

    }

    private fun fetchData() {
        retrofit.create(WebService::class.java)
                .getFlagList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(object : Observer<ServerResponse> {
                    override fun onComplete() {
                        Log.d("#####", "API Completed")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("#####", "API Subscribed")
                    }

                    override fun onNext(t: ServerResponse) {
                        if (t != null) {
                            if (t.worldpopulation != null) {
                                imageRecyclerAdapter = ImageRecyclerAdapter(t.worldpopulation, this@MainActivity)
                                recyclerView.adapter = imageRecyclerAdapter
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("#####", "" + e.message)
                    }
                })
    }
}
