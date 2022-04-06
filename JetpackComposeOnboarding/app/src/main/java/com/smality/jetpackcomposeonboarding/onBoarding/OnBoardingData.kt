package com.smality.jetpackcomposeonboarding.onBoarding

import com.smality.jetpackcomposeonboarding.R
data class OnBoardingData(val titleR: Int, val textR: Int, val imageR: Int)

fun getData(): List<OnBoardingData> {
    return listOf(
        OnBoardingData(
            titleR = R.string.onBoardingTitle1,
            textR = R.string.onBoardingText1,
            imageR = R.drawable.on_boarding1
        ),
        OnBoardingData(
            titleR = R.string.onBoardingTitle2,
            textR = R.string.onBoardingText2,
            imageR = R.drawable.on_boarding2
        ),
        OnBoardingData(
            titleR = R.string.onBoardingTitle3,
            textR = R.string.onBoardingText3,
            imageR = R.drawable.on_boarding3
        ),
    )
}