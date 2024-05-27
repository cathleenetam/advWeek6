package com.duodinamika.advweek4.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.duodinamika.advweek4.model.Student
import com.google.gson.Gson

class DetailViewModel: ViewModel() {
    val studentLD = MutableLiveData<Student>()

//    fun fetch() {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
//    }

    fun fetch(studentId: String, context: Context) {
        // Create a Volley request queue
        val queue: RequestQueue = Volley.newRequestQueue(context)

        // Define the URL for the student data
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        // Create a JSON request
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                // Parse the JSON response using GSON
                val gson = Gson()
                val student = gson.fromJson(response.toString(), Student::class.java)

                // Update the LiveData with the retrieved student data
                studentLD.value = student
            },
            { _ ->
                // Handle errors here
            })

        // Add the request to the request queue
        queue.add(jsonObjectRequest)
    }

}