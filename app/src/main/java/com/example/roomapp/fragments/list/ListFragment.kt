package com.example.roomapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var userViewMode: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

        view.btn_add_items.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Recycler View & Adapter
        val myAdapter = ListAdapter()
        val rv_list = view.rv_list_items
        rv_list.adapter = myAdapter
        rv_list.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        userViewMode = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewMode.readAllData.observe(viewLifecycleOwner, Observer {
            //Toast.makeText(requireContext(),"....... Changed ....... | "+it.size, Toast.LENGTH_LONG).show()
            myAdapter.setDataList(it)
        })
        setHasOptionsMenu(true)
        return  view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_delete)
            deleteAllUser()

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _,_->
            userViewMode.deleteAllUser()
            Toast.makeText(requireContext(),"Deleting All Users", Toast.LENGTH_LONG).show()
                    }
        builder.setNegativeButton("Noo"){_,_->

        }
        builder.setTitle("Delete All User")
        builder.setMessage("Are You Sure you want to delete All User")
        builder.create().show()

    }
}