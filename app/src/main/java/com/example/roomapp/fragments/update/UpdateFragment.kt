package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.et_age
import kotlinx.android.synthetic.main.fragment_update.et_first_name
import kotlinx.android.synthetic.main.fragment_update.et_last_name
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    lateinit var user:User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userId = arguments?.getString("id") ?:""

        userViewModel.getUserById(userId)

        userViewModel.user.observe(viewLifecycleOwner, Observer {
            user=it
            fillTextFilde(it,view)
        })

        view.btn_update.setOnClickListener{
            updatetUser()
        }

        view.btn_delete.setOnClickListener{
            deleteUser()
        }

        setHasOptionsMenu(true)

        return view;
    }

    private fun deleteUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _,_->
            userViewModel.deleteUser(user)
            Toast.makeText(requireContext(),"Deleted ${user.first_name}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Noo"){_,_->

        }
        builder.setTitle("Delete User ${user.id}")
        builder.setMessage("Are You Sure you want to delete")
        builder.create().show()


    }

    private fun fillTextFilde(user: User, view: View) {

        val first_name: TextView = view.findViewById(R.id.et_first_name)
        val last_name: TextView = view.findViewById(R.id.et_last_name)
        val age: TextView = view.findViewById(R.id.et_age)

        first_name.text = user.first_name
        last_name.text = user.last_name
        age.text = user.age.toString()

    }

    private fun updatetUser() {

        var first_name = et_first_name.text.toString()
        var last_name = et_last_name.text.toString()
        var age = et_age.text

        if(inputCheck(first_name,last_name,age)){
            val user = User(user.id,
                first_name,
                last_name,
                Integer.parseInt(age.toString())
            )

            userViewModel.updateUser(user)

            Toast.makeText(requireContext(),"Updated Ok", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Fill All Fields", Toast.LENGTH_LONG).show()
        }
    }

    fun inputCheck(first_name: String, last_name: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(first_name)&& TextUtils.isEmpty(last_name)&&age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_delete)
            deleteUser()

        return super.onOptionsItemSelected(item)
    }
}