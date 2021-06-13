package com.submission.yusuf.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.submission.yusuf.R
import com.submission.yusuf.utils.DataCatalogue
import com.submission.yusuf.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyHomeMovie = DataCatalogue.generateCatalogueFilm()
    private val dummyHomeTvShow = DataCatalogue.generateCatalogueTVShows()



    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadAllMovie(){
        //Movie Test
        onView(withId(R.id.mov_fragment))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mov_fragment))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyHomeMovie.size))
        onView(withId(R.id.mov_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.img_cover))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name))
            .check(matches(ViewMatchers.withText(dummyHomeMovie[0].title)))
        pressBack()

        onView(withId(R.id.mov_fragment))
            .check(matches(isDisplayed()))

    }

    @Test
    fun loadFavoritedMovie(){
        onView(withId(R.id.mov_fragment))
                .check(matches(isDisplayed()))
        onView(withId(R.id.mov_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.withText("Favorite Movies")).perform(click())
        onView(withId(R.id.movie_item_favorit))
                .check(matches(isDisplayed()))
        onView(withId(R.id.movie_item_favorit)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        click()
                ))
        onView(withId(R.id.img_cover)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name)).check(matches(ViewMatchers.withText(dummyHomeMovie[0].title)))
        onView(withId(R.id.favorite_menu)).perform(click())
        pressBack()
    }

    @Test
    fun loadAllTvShow(){
        onView(withId(R.id.mov_fragment))
                .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV SHOW")).perform(click())
        onView(withId(R.id.tvshow_item))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvshow_item))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyHomeTvShow.size))
        onView(withId(R.id.tvshow_item)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.tv_title_name))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name))
            .check(matches(ViewMatchers.withText(dummyHomeTvShow[0].title)))
        pressBack()
    }

    @Test
    fun loadTVShowFavorite(){
        onView(withId(R.id.mov_fragment))
                .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV SHOW")).perform(click())
        onView(withId(R.id.tvshow_item)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshow_item)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.withText("Favorite Tv Show")).perform(click())
        onView(withId(R.id.tvshow_item_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshow_item_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_name))
                .check(matches(isDisplayed()))
        onView(withId(R.id.tv_description))
                .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_name))
                .check(matches(ViewMatchers.withText(dummyHomeTvShow[0].title)))
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }
}