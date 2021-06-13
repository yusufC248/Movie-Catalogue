package com.submission.yusuf.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.yusuf.databinding.ActivityTvShowFragmentBinding
import com.submission.yusuf.viewmodel.TvShowViewModelFactory
import com.submission.yusuf.vo.Status

class TvShowFragment: Fragment() {


   private lateinit var activityTvShowFragmentBinding: ActivityTvShowFragmentBinding
    private val binding get() = activityTvShowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityTvShowFragmentBinding = ActivityTvShowFragmentBinding.inflate(layoutInflater, container, false)
        return activityTvShowFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = TvShowViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()


            activityTvShowFragmentBinding.progressBar.visibility = View.VISIBLE
            viewModel.returnTvShow().observe(viewLifecycleOwner, { tvshow ->
                if(tvshow != null){
                    when(tvshow.status){
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS ->{
                            binding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvshow.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(activityTvShowFragmentBinding.tvshowItem) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }

    }

}