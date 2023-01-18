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

    private fun validation():Boolean{
        var isValid=true

        if (binding.noteMsg.text.toString().isNullOrEmpty()){
            isValid=false
            toast("Enter message")
        }
        return isValid
    }


}