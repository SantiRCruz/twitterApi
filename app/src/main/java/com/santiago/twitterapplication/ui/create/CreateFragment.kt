package com.santiago.twitterapplication.ui.create

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.santiago.twitterapplication.databinding.FragmentCreateBinding
import com.santiago.twitterapplication.models.database.DBManager
import com.santiago.twitterapplication.models.users.Data
import kotlinx.android.synthetic.main.fragment_actualizar.*
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment() {


    private var _binding: FragmentCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
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
                val cm =
                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) == true || capabilities?.hasTransport(
                        android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH
                    ) == true || capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) == true || capabilities?.hasTransport(
                        android.net.NetworkCapabilities.TRANSPORT_ETHERNET
                    ) == true
                ) {
                    Toast.makeText(
                        requireContext(),
                        "se encuentra con internet",
                        Toast.LENGTH_SHORT
                    ).show()

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