package com.santiago.twitterapplication.ui.actualizar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.databinding.FragmentActualizarBinding
import com.santiago.twitterapplication.models.Constants
import com.santiago.twitterapplication.models.database.DBManager
import com.santiago.twitterapplication.models.users.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_actualizar.*

class ActualizarFragment : Fragment() {

    private var _binding: FragmentActualizarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentActualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBManager(requireContext())
        //listar id
        var list=dbManager.listarId(Constants.LIST_ID)
        var partesLista = list.toString().split(" ")
        editTextActualizarEmail.setText(partesLista[1])
        editTextActualizarFirstName.setText(partesLista[2])
        editTextActualizarLastName.setText(partesLista[3])
        Picasso.get().load(partesLista[4].replace("]","")).into(imageViewActualizar)

        //actualizar
        buttonActualizarActualizar.setOnClickListener {
            var result  = dbManager.actualizar(Data(Constants.LIST_ID,editTextActualizarEmail.text.toString(),editTextActualizarFirstName.text.toString(),editTextActualizarLastName.text.toString()))
            if (result>0){
                Toast.makeText(requireContext(), "se actualizo correctamente", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).navigate(R.id.navigation_users)
            }else{
                Toast.makeText(requireContext(), "no se actualizo correctamente", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}