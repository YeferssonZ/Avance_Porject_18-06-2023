package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_opciones_alumno.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OpcionesFragmentAlumno : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var alumnoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_opciones_alumno, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("AlumnoData", Context.MODE_PRIVATE)
        alumnoId = sharedPreferences.getInt("alumnoId", -1)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Cerrar sesión") { dialog, which ->
                val logout = Intent(activity, LoginActivity::class.java)
                startActivity(logout)
                activity?.finish()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
            }
            .setCancelable(true)

        view.imgLogout.setOnClickListener {
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        view.imgCuaderno.setOnClickListener {
            Log.d("AlumnoId", "Valor de alumnoId: $alumnoId")
            val intent = Intent(activity, ListadoCursos::class.java)
            intent.putExtra("alumnoId", alumnoId)
            startActivity(intent)
        }

        /*
        view.imgCuaderno.setOnClickListener {
            val cursoDialogFragment = CursoDialogFragment()
            cursoDialogFragment.show(childFragmentManager, "CursoDialog")
        }
        */

        return view
    }

    fun updateAlumnoId(alumnoId: Int) {
        this.alumnoId = alumnoId
    }

    companion object {
        fun newInstance(alumnoId: Int): OpcionesFragmentAlumno {
            val fragment = OpcionesFragmentAlumno()
            val bundle = Bundle()
            bundle.putInt("alumnoId", alumnoId)
            fragment.arguments = bundle
            return fragment
        }
    }
}