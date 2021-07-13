package com.santiago.twitterapplication.ui.notifications

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.databinding.FragmentNotificationsBinding
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.Constants
import com.santiago.twitterapplication.models.Create
import com.santiago.twitterapplication.models.database.DBManager
import com.santiago.twitterapplication.models.users.Data
import kotlinx.android.synthetic.main.fragment_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbManager = DBManager(requireContext())
        buttonCreateCreate.setOnClickListener {
            if (editTextCreateEmail.text.toString()
                    .isEmpty() || editTextCreateFirstName.text.toString()
                    .isEmpty() || editTextCreateLastName.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "debe completar todos los espacios",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) == true || capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH) == true || capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) == true || capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_ETHERNET) == true) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    service.create(Create(editTextCreateFirstName.text.toString(),editTextCreateLastName.text.toString())).enqueue(object : Callback<Create>{
                        override fun onResponse(call: Call<Create>?, response: Response<Create>?) {
                         if (response?.code()==201){
                             Toast.makeText(requireContext(), "se inserto correctamente", Toast.LENGTH_SHORT).show()
                             editTextCreateEmail.setText("")
                             editTextCreateFirstName.setText("")
                             editTextCreateLastName.setText("")
                         }else{
                             Toast.makeText(requireContext(), "no se inserto correctamente", Toast.LENGTH_SHORT).show()
                         }
                        }
                        override fun onFailure(call: Call<Create>?, t: Throwable?) {
                            Log.e("ERROR>>>",t?.message.toString())
                        }

                    })
                } else {
                    var result = dbManager.insertar(
                        Data(
                            editTextCreateEmail.text.toString(),
                            editTextCreateFirstName.text.toString(),
                            editTextCreateLastName.text.toString(),
                            "https://reqres.in/img/faces/11-image.jpg"
                        )
                    )
                    if (result > 0) {
                        Toast.makeText(
                            requireContext(),
                            "se inserto correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        editTextCreateEmail.setText("")
                        editTextCreateFirstName.setText("")
                        editTextCreateLastName.setText("")
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "no se inserto correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}