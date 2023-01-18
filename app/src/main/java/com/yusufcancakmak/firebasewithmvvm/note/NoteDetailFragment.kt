package com.yusufcancakmak.firebasewithmvvm.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yusufcancakmak.firebasewithmvvm.data.model.Note

import com.yusufcancakmak.firebasewithmvvm.databinding.FragmentNoteDetailBinding
import com.yusufcancakmak.firebasewithmvvm.util.UiState
import com.yusufcancakmak.firebasewithmvvm.util.hide
import com.yusufcancakmak.firebasewithmvvm.util.show
import com.yusufcancakmak.firebasewithmvvm.util.toast

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    val TAG: String ="Note Detail Fragment"
    lateinit var binding: FragmentNoteDetailBinding
    val viewmodel:NoteViewModel by viewModels ()
    var isEdit=false
    var objeNote: Note?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        binding.updateBtn.setOnClickListener {
            if (isEdit){
                updateNote()
            }else{
                createNote()
            }
        }

    }
    private fun updateUI(){
        val type = arguments?.getString("type",null)

        type?.let {
            when(it){
                "view" ->{
                    isEdit =false
                    binding.noteMsg.isEnabled=false
                    objeNote= arguments?.getParcelable("note")
                    binding.noteMsg.setText(objeNote?.text.toString())
                    binding.updateBtn.hide()

                }
                "create" ->{
                    isEdit =false
                    binding.updateBtn.setText("Create")

                }
                "edit" ->{
                    isEdit =true
                    objeNote =arguments?.getParcelable("note")
                    binding.noteMsg.setText(objeNote?.text.toString())
                    binding.updateBtn.setText("update")

                }
            }
        }
    }
    private fun createNote(){
        binding.updateBtn.setOnClickListener {
            if (validation()){
                viewmodel.addNote(Note(
                    id = "",
                    text = binding.noteMsg.text.toString(),
                    date = Date()

                ))

            }
        }
        viewmodel.addnote.observe(viewLifecycleOwner){ state->
            when(state){

                is UiState.Loading ->{
                    binding.progrBtn.show()
                    binding.updateBtn.text=""
                }
                is UiState.Failure ->{
                    binding.progrBtn.hide()
                    binding.updateBtn.text="Create"
                    toast(state.error)
                }

                is UiState.Success ->{
                    binding.progrBtn.hide()
                    binding.updateBtn.text="CREATE"
                    toast(state.data)
                }
            }

        }

    }
    private fun updateNote(){
            if (validation()){
                viewmodel.updateNote(Note(
                    id = objeNote?.id ?:"",
                    text = binding.noteMsg.text.toString(),
                    date = Date()
                ))
            }

        viewmodel.updatenote.observe(viewLifecycleOwner){ state->
            when(state){

                is UiState.Loading ->{
                    binding.progrBtn.show()
                    binding.updateBtn.text=""
                }
                is UiState.Failure ->{
                    binding.progrBtn.hide()
                    binding.updateBtn.text="update"
                    toast(state.error)
                }

                is UiState.Success ->{
                    binding.progrBtn.hide()
                    binding.updateBtn.text="update"
                    toast(state.data)
                }
            }

        }

    }

    private fun validation():Boolean{
        var isValid=true

        if (binding.noteMsg.text.toString().isNullOrEmpty()){
            isValid=false
            toast("Enter message")
        }
        return isValid
    }


}