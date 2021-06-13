package com.submission.yusuf.favorite_interface.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.submission.yusuf.databinding.FragmentFavoriteMovieBinding
import com.submission.yusuf.viewmodel.ViewModelfactory

class MovieFavorite: Fragment() {
    private lateinit var activityMovieFavoriteMovieBinding: FragmentFavoriteMovieBinding
    private val binding get() = activityMovieFavoriteMovieBinding

    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityMovieFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.movieItemFavorit)

        if (activity != null){
            val factory = ViewModelfactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]


            adapter = MovieFavoriteAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavorite().observe(viewLifecycleOwner, {movie ->
                binding.progressBar.visibility = View.GONE
                adapter.submitList(movie)
                adapter.notifyDataSetChanged()
            })
            binding.movieItemFavorit.layoutManager = LinearLayoutManager(context)
            binding.movieItemFavorit.setHasFixedSize(true)
            binding.movieItemFavorit.adapter = adapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            adapter = MovieFavoriteAdapter()
            if (view != null){
                val swiped = viewHolder.adapterPosition
                val filmEntity = adapter.getSwipeData(swiped)
                filmEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, "Cancel delete?", Snackbar.LENGTH_LONG)
                snackbar.setAction("OK") { v ->
                    filmEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()

            }
        }
    })
}