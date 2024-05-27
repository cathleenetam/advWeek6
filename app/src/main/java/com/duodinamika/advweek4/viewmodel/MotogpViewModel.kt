package com.duodinamika.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.duodinamika.advweek4.model.Motogp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MotogpViewModel(application: Application): AndroidViewModel(application) {
    val motogpLD = MutableLiveData<ArrayList<Motogp>>()
    val TAG = "volleyTagMotogp"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/motogp/motogp.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Motogp>>(){}.type
                val result = Gson().fromJson<List<Motogp>>(it, sType)
                motogpLD.value = result as ArrayList<Motogp>?
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}