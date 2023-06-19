package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CursoAdapter : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    private val cursos = ArrayList<AppCurso>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_cursos, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursos[position]
        holder.bind(curso)
    }

    override fun getItemCount(): Int {
        return cursos.size
    }

    fun setCursos(cursos: List<AppCurso>) {
        this.cursos.clear()
        this.cursos.addAll(cursos)
        notifyDataSetChanged()
    }

    fun actualizarNombreAlumno(alumnoId: Int, nombreAlumno: String) {
        cursos.forEach { curso ->
            if (curso.alumno == alumnoId) {
                curso.nombreAlumno = nombreAlumno
            }
        }
        notifyDataSetChanged()
    }

    fun actualizarNombreCurso(cursoId: Int, nombreCurso: String) {
        val curso = cursos.find { it.curso == cursoId }
        curso?.nombreCurso = nombreCurso
        curso?.let {
            val position = cursos.indexOf(it)
            notifyItemChanged(position)
        }
    }

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(curso: AppCurso) {
            val txtCursoId: TextView = itemView.findViewById(R.id.txtCursoId)
            val txtAlumnoId: TextView = itemView.findViewById(R.id.txtAlumnoId)
            val txtCursoNombre: TextView = itemView.findViewById(R.id.txtCursoNombre)

            txtCursoId.text = "ID del curso: ${curso.id}"
            txtAlumnoId.text = "ID del alumno: ${curso.alumno}"
            txtCursoNombre.text = "Nombre del curso: ${curso.curso}"

            // Verificar si se ha actualizado el nombre del alumno y mostrarlo si está disponible
            if (!curso.nombreAlumno.isNullOrEmpty()) {
                txtAlumnoId.text = "Nombre del alumno: ${curso.nombreAlumno}"
            } else {
                txtAlumnoId.text = "ID del alumno: ${curso.alumno}"
            }


            // Verificar si se ha actualizado el nombre del curso y mostrarlo si está disponible
            if (!curso.nombreCurso.isNullOrEmpty()) {
                txtCursoNombre.text = "Nombre del curso: ${curso.nombreCurso}"
            }
        }
    }
}
