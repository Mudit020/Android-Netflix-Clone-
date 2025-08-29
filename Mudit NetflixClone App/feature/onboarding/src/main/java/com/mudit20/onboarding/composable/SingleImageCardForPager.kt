package com.mudit20.onboarding.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mudit20.onboarding.data.Page
import kotlin.math.absoluteValue

@Composable
fun SingleImageCard(
    page: Int,
    pagerState: PagerState,
    item: Page
) {
    // Calculate the page offset for scale animation
    val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

    val scale = 0.8f + (1f - pageOffset.absoluteValue.coerceIn(0f, 1f)) * 0.2f

    Card(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(
            model = item.Image,
            contentDescription = "Onboarding Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


