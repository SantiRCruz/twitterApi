package com.santiago.twitterapplication.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.twitterapplication.controllers.RecyclerUsersAdapter
import com.santiago.twitterapplication.databinding.FragmentUsersBinding
import com.santiago.twitterapplication.models.database.DBManager
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager=DBManager(requireContext())
        recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
        val adapater = RecyclerUsersAdapter(dbManager.listar())
        recyclerViewUsers.adapter = adapater


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}