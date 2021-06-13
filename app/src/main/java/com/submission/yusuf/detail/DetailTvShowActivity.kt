package com.submission.yusuf.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.submission.yusuf.R
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.databinding.ActivityDetailFilmBinding
import com.submission.yusuf.databinding.FragmentModuleContentBinding
import com.submission.yusuf.utils.DataHelper
import com.submission.yusuf.viewmodel.TvShowViewModelFactory
import com.submission.yusuf.vo.Status
import kotlinx.android.synthetic.main.fragment_module_content.*

class DetailTvShowActivity: AppCompatActivity() {

    companion object{
        const val KEY_FILM = "extra_film"

    }



    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding
    private lateinit var activityDetailFilmBinding: ActivityDetailFilmBinding



    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        fragmentModuleContentBinding = activityDetailFilmBinding.detailContent
        setContentView(activityDetailFilmBinding.root)
        val factory = TvShowViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val courseId = extras.getString(KEY_FILM)
            if (courseId != null) {



                viewModel.setSelectedCourse(courseId)
                viewModel.tvshowModule.observe(this, { courseWithModuleResource ->
                    if (courseWithModuleResource != null) {
                        when (courseWithModuleResource.status) {
                            Status.LOADING -> activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                activityDetailFilmBinding.progressBar.visibility = View.GONE
                                activityDetailFilmBinding.content.visibility = View.VISIBLE
                                populateCatalogueTvShow(courseWithModuleResource.data.mCourse)
                            }
                            Status.ERROR -> {
                                activityDetailFilmBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }



    private fun populateCatalogueTvShow(tvsEntity: TvsEntity){
        tv_title_name.text = tvsEntity.title
        tv_description.text = tvsEntity.description
        tv_rating.text = tvsEntity.rating
        DataHelper.setImageView(this@DetailTvShowActivity, tvsEntity.imagePath, img_cover)
        supportActionBar?.title = tvsEntity.title


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val factory = TvShowViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        menuInflater.inflate(R.menu.menu, menu)
        this.menu = menu
        viewModel.tvshowModule.observe(this,{ courseWithModule ->
            if (courseWithModule != null) {
                when (courseWithModule.status) {
                    Status.LOADING -> activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (courseWithModule.data != null) {
                        activityDetailFilmBinding.progressBar.visibility = View.GONE
                        val state = courseWithModule.data.mCourse.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        activityDetailFilmBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val factory = TvShowViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        if (item.itemId == R.id.favorite_menu) {
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return  super.onSupportNavigateUp()
    }

    private fun setFavoriteState(state: Boolean){
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.favorite_menu)
        if(state){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_full_foreground)
            Toast.makeText(this, "This Item added to Favorite", Toast.LENGTH_SHORT).show()
        }else{
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_foreground)

        }
    }
}