package com.submission.yusuf.utils

import com.submission.yusuf.data.source.local.entity.*
import com.submission.yusuf.data.source.remote.response.FilmResponse
import com.submission.yusuf.data.source.remote.response.TvsResponse

object DataCatalogue {

    fun generateDummyMovieWithModules(movie: FilmEntity, favorited: Boolean): MovieWithModule {
        movie.favorited = favorited
        return MovieWithModule(movie, generateDummyModules(movie.filmId))
    }

    fun generateDummyTvWithModules(tv: TvsEntity, favorited: Boolean): TvShowWithModule {
        tv.favorited = favorited
        return TvShowWithModule(tv, generateDummyModules(tv.filmId))
    }

    fun generateCatalogueFilm(): List<FilmEntity>{
        val movieList = ArrayList<FilmEntity>()

        movieList.add(
            FilmEntity(
            filmId = "mvs1",
            title = "GODZILLA VS. KONG",
                description = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
            rating = "87"
            )
        )
        movieList.add(
            FilmEntity(
                filmId = "mvs2",
                title = "ZACK SNYDER'S JUSTICE LEAGUE",
                description = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                rating = "85"
            )
        )
        movieList.add(
            FilmEntity(
                filmId = "mvs3",
                title = "CHAOS WALKING",
                description = "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                rating = "74"
            )
        )
        movieList.add(
            FilmEntity(
                filmId = "mvs4",
                title = "TOM & JERRY",
                description = "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                rating = "73"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs5",
                title = "MORTAL KOMBAT",
                description = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                rating = "74"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs6",
                title = "PARASITE",
                description = "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg",
                rating = "85"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs7",
                title = "ASHFALL",
                description = "A group of unlikely heroes from across the Korean peninsula try to save the day after a volcano erupts on the mythical and majestic Baekdu Mountain.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zoeKREZ2IdAUnXISYCS0E6H5BVh.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/h9DIlghaiTxbQbt1FIwKNbQvEL.jpg",
                rating = "65"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs8",
                title = "SOUL",
                description = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                rating = "83"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs9",
                title = "AVENGERS ENDGAME",
                description = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
                rating = "83"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs10",
                title = "AVENGERS:  INFINITY WAR",
                description = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg",
                rating = "83"
            )
        )
         movieList.add(
            FilmEntity(
                filmId = "mvs11",
                title = "SPIDERMAN: INTO THE SPIDER-VERSE",
                description = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/7d6EY00g1c39SGZOoCJ5Py9nNth.jpg",
                rating = "84"
            )
        )
        return movieList
    }

    fun generateCatalogueTVShows(): List<TvsEntity>{
        val tvshowList = ArrayList<TvsEntity>()
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs1",
                        title = "FALCON AND THE WINTER SOLDIER",
                        description = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                        rating = "79"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs2",
                        title = "THE SIMPSONS",
                        description = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                        rating = "78"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs3",
                        title = "THE GOOD DOCTOR",
                        description = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                        rating = "86"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs4",
                        title = "THE FLASH",
                        description = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                        rating = "77"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs5",
                        title = "THE INVICIBLE",
                        description = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                        rating = "89"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs6",
                        title = "GREY'S ANATOMY",
                        description = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                        rating = "82"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs7",
                        title = "RIVERDALE",
                        description = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                        rating = "86"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs8",
                        title = "LUCIFER",
                        description = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                        rating = "85"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs9",
                        title = "WANDA VISION",
                        description = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                        rating = "84"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs10",
                        title = "WHO KILLED SARA?",
                        description = "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/pLG4ihU1d2XkQbASQDjsFu9U7d9.jpg",
                        rating = "79"
                )
        )
        tvshowList.add(
            TvsEntity(
                        filmId = "tvs11",
                        title = "SUPERMAN & LUOIS",
                        description = "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                        imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                        background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                        rating = "79"
                )
        )
        return tvshowList
    }

    fun generateDummyCatalogueTVShows(): List<TvsResponse>{
        val tvshowList = ArrayList<TvsResponse>()
        tvshowList.add(
            TvsResponse(
                filmId = "tvs1",
                title = "FALCON AND THE WINTER SOLDIER",
                description = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                rating = "79"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs2",
                title = "THE SIMPSONS",
                description = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                rating = "78"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs3",
                title = "THE GOOD DOCTOR",
                description = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                rating = "86"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs4",
                title = "THE FLASH",
                description = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                rating = "77"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs5",
                title = "THE INVICIBLE",
                description = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                rating = "89"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs6",
                title = "GREY'S ANATOMY",
                description = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                rating = "82"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs7",
                title = "RIVERDALE",
                description = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                rating = "86"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs8",
                title = "LUCIFER",
                description = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                rating = "85"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs9",
                title = "WANDA VISION",
                description = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                rating = "84"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs10",
                title = "WHO KILLED SARA?",
                description = "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/pLG4ihU1d2XkQbASQDjsFu9U7d9.jpg",
                rating = "79"
            )
        )
        tvshowList.add(
            TvsResponse(
                filmId = "tvs11",
                title = "SUPERMAN & LUOIS",
                description = "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                rating = "79"
            )
        )
        return tvshowList
    }

    fun generateDummyCatalogueFilm(): List<FilmResponse>{
        val movieList = ArrayList<FilmResponse>()

        movieList.add(
            FilmResponse(
                filmId = "mvs1",
                title = "GODZILLA VS. KONG",
                description = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                rating = "87"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs2",
                title = "ZACK SNYDER'S JUSTICE LEAGUE",
                description = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                rating = "85"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs3",
                title = "CHAOS WALKING",
                description = "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                rating = "74"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs4",
                title = "TOM & JERRY",
                description = "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                rating = "73"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs5",
                title = "MORTAL KOMBAT",
                description = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                rating = "74"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs6",
                title = "PARASITE",
                description = "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg",
                rating = "85"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs7",
                title = "ASHFALL",
                description = "A group of unlikely heroes from across the Korean peninsula try to save the day after a volcano erupts on the mythical and majestic Baekdu Mountain.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zoeKREZ2IdAUnXISYCS0E6H5BVh.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/h9DIlghaiTxbQbt1FIwKNbQvEL.jpg",
                rating = "65"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs8",
                title = "SOUL",
                description = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                rating = "83"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs9",
                title = "AVENGERS ENDGAME",
                description = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
                rating = "83"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs10",
                title = "AVENGERS:  INFINITY WAR",
                description = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg",
                rating = "83"
            )
        )
        movieList.add(
            FilmResponse(
                filmId = "mvs11",
                title = "SPIDERMAN: INTO THE SPIDER-VERSE",
                description = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                background = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/7d6EY00g1c39SGZOoCJ5Py9nNth.jpg",
                rating = "84"
            )
        )
        return movieList
    }

    fun generateDummyModules(courseId: String): List<ModuleEntity> {

        val modules = ArrayList<ModuleEntity>()

        modules.add(ModuleEntity(
            "{$courseId}m1",
            courseId,
            "",
            0, false))
        modules.add(ModuleEntity("{$courseId}m2",
            courseId,
            "",
            1, false))
        modules.add(
            ModuleEntity("{$courseId}m3",
            courseId,
            "",
            2, false)
        )
        modules.add(ModuleEntity("{$courseId}m4",
            courseId,
            "",
            3, false))
        modules.add(ModuleEntity("{$courseId}m5",
            courseId,
            "",
            4, false))

        modules.add(ModuleEntity("{$courseId}m6",
            courseId,
            "",
            5, false))

        modules.add(
            ModuleEntity("{$courseId}m7",
                courseId,
                "",
                6, false)
        )

        return modules
    }
}