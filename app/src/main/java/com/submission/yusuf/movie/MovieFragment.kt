package com.submission.yusuf.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.submission.yusuf.databinding.ActivityMovieFragmentBinding

import com.submission.yusuf.viewmodel.ViewModelfactory
import com.submission.yusuf.vo.Status


class MovieFragment : Fragment(){
    private lateinit var activityMovieFragmentBinding: ActivityMovieFragmentBinding
    private val binding get() = activityMovieFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       activityMovieFragmentBinding = ActivityMovieFragmentBinding.inflate(layoutInflater, container, false)
        return activityMovieFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){

            val factory = ViewModelfactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.returnMovie().observe(viewLifecycleOwner, {movies ->
                if (movies != null){
                    when(movies.status){
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS ->{
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(activityMovieFragmentBinding.movFragment){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }


        }

    }


}
