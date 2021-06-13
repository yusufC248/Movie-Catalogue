package com.submission.yusuf.favorite_interface.tvshow

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

import com.submission.yusuf.databinding.FragmentFavoriteTvshowBinding
import com.submission.yusuf.viewmodel.TvShowViewModelFactory

class TvShowFavorite: Fragment() {
    private lateinit var activityFragmentFavoriteTvshowBinding: FragmentFavoriteTvshowBinding
    private val binding get() = activityFragmentFavoriteTvshowBinding
    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var adapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityFragmentFavoriteTvshowBinding = FragmentFavoriteTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.tvshowItemFavorite)

        if (activity != null){
            val factory = TvShowViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]


            adapter = TvShowFavoriteAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavorite().observe(viewLifecycleOwner, {movie ->
                binding.progressBar.visibility = View.GONE
                adapter.submitList(movie)
                adapter.notifyDataSetChanged()
            })
            binding.tvshowItemFavorite.layoutManager = LinearLayoutManager(context)
            binding.tvshowItemFavorite.setHasFixedSize(true)
            binding.tvshowItemFavorite.adapter = adapter
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

            adapter = TvShowFavoriteAdapter()
            if (view != null){
                val swiped = viewHolder.adapterPosition
                val tvsEntity = adapter.getSwipeData(swiped)
                tvsEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, "Cancel delete?", Snackbar.LENGTH_LONG)
                snackbar.setAction("OK") { v ->
                    tvsEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()

            }
        }
    })
}