package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var userViewMode: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        userViewMode = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btn_add.setOnClickListener{
            insertUser()
        }


        return view
    }

    private fun insertUser() {

        var first_name = et_first_name.text.toString()
        var last_name = et_last_name.text.toString()
        var age = et_age.text

        if(inputCheck(first_name,last_name,age)){
            val user = User(
                0,
                first_name,
                last_name,
                Integer.parseInt(age.toString())
            )
            userViewMode.addUser(user)
            Toast.makeText(requireContext(),"Added Ok",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Fill All Fields",Toast.LENGTH_LONG).show()
        }
    }

    fun inputCheck(first_name: String, last_name: String, age:Editable): Boolean {
        return !(TextUtils.isEmpty(first_name)&&TextUtils.isEmpty(last_name)&&age.isEmpty())
    }

}