package com.mudit20.onboarding.data

import androidx.annotation.DrawableRes
import com.mudit20.onboarding.R

data class Page(
    val title: String="",
    val descrpition:String="",
    @DrawableRes val Image:Int=0

)

val pagelist= listOf<Page>(

    Page("DEADPOOL 2024",
        "Deadpool & Wolverine (2024) unites the Merc with a Mouth and Wolverine. Directed by Shawn Levy, it's the 34th MCU film.",
          R.drawable.img1),
    Page("F1 2025","F1 (2025) stars Brad Pitt as a retired driver mentoring a rookie. Directed by Joseph Kosinski, produced by Jerry Bruckheimer & Lewis Hamilton.",
        R.drawable.img2),
    Page("MISSON IMPOSSIBLE:DEAD RECKONING","Mission: Impossible – Dead Reckoning Part One is a 2023 American spy action film. Tom Cruise returns as Ethan Hunt, leading an IMF team against a dangerous AI.",
        R.drawable.img3)

)


