package com.duodinamika.advweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.duodinamika.advweek4.R
import com.duodinamika.advweek4.databinding.StudentListItemBinding
import com.duodinamika.advweek4.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var binding:StudentListItemBinding) : RecyclerView.ViewHolder(binding.root){
//        val lblId: TextView
//        val lblName:TextView
//        val btnDetail: Button
//        val imgStudent: ImageView
//        init {
//            lblId = view.findViewById(R.id.lblId)
//            lblName = view.findViewById(R.id.lblName)
//            btnDetail = view.findViewById(R.id.btnDetail)
//            imgStudent = view.findViewById(R.id.imgStudent)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this



//        holder.lblId.text = studentList[position].id
//        holder.lblName.text = studentList[position].name
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//
//        picasso.build().load(studentList[position].photoUrl).into(holder.imgStudent)
//
//
//        var studentId = holder.lblId.text.toString()
//
//        holder.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studentId)
//            Navigation.findNavController(it).navigate(action)
//        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            //exception.printStackTrace()
            Log.e("picasso_error", exception.toString())
        }
        picasso.build().load(studentList[position].photoUrl)
            .into(holder.binding.imgStudent, object:Callback {
                override fun onSuccess() {
                    holder.binding.progressImage.visibility = View.INVISIBLE
                    holder.binding.imgStudent.visibility = View.VISIBLE

                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())

                }

            })


    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val id = v.tag.toString()
        val action = StudentListFragmentDirections.actionStudentDetail(id)
        Log.e("xxx", "onButtonClick: id=$id", )
        Navigation.findNavController(v).navigate(action)
    }
}
