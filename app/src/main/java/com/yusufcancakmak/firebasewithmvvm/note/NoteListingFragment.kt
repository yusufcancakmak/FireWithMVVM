package com.yusufcancakmak.firebasewithmvvm.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcancakmak.firebasewithmvvm.R
import com.yusufcancakmak.firebasewithmvvm.adapter.NoteListiningAdapter

import com.yusufcancakmak.firebasewithmvvm.databinding.FragmentNoteListingBinding
import com.yusufcancakmak.firebasewithmvvm.util.UiState
import com.yusufcancakmak.firebasewithmvvm.util.hide
import com.yusufcancakmak.firebasewithmvvm.util.show
import com.yusufcancakmak.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {
    val TAG: String ="Note Listining Fragment"
    lateinit var binding: FragmentNoteListingBinding
    val viewModel:NoteViewModel by viewModels()
    val adapter by lazy {
        NoteListiningAdapter(
            onItemClicked = { pos,item->


            },
            onEditCliked = {pos, item ->

            },
            onDeleteCicked = {pos, item ->

            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager=LinearLayoutManager(requireContext())
        binding.rv.adapter=adapter
        binding.buton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment)
        }
        viewModel.getNotes()
        viewModel.note.observe(viewLifecycleOwner){ state->
            when(state){

                is UiState.Loading ->{
                    binding.progressBar.show()
                }
                is UiState.Failure ->{
                    binding.progressBar.hide()
                    toast(state.error)
                }

                is UiState.Success ->{
                binding.progressBar.hide()
                    adapter.updateList(state.data.toMutableList())
                }
            }

        }
    }


    }
