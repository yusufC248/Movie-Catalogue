package com.submission.yusuf.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.submission.yusuf.R
import com.submission.yusuf.favorite_interface.home.MovieFavorite
import com.submission.yusuf.favorite_interface.tvshow.TvShowFavorite
import com.submission.yusuf.movie.MovieFragment
import com.submission.yusuf.tvshow.TvShowFragment

class SectionPagerAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.MovieTitle, R.string.TvTitle, R.string.title_favorite_movie, R.string.title_favorite_tvshows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            2 -> MovieFavorite()
            3 -> TvShowFavorite()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 4

}