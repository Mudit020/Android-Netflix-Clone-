package com.mudit20.onboarding.screen

import android.R.attr.scaleX
import android.R.attr.scaleY
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import com.mudit20.onboarding.data.Page
import kotlin.math.absoluteValue
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mudit20.onboarding.composable.SingleImageCard
import com.mudit20.onboarding.data.pagelist
import org.jetbrains.annotations.Async
import kotlin.text.get


@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isEnabled: Boolean = true,
    backgroundColor: Color = Color.Red,
    contentColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun OnBoardScreen(navigation: () -> Unit, modifier: Modifier = Modifier) {
    val pagerstate = rememberPagerState(initialPage = 0) {
        pagelist.size 
    }
    val currentitem by remember {
        derivedStateOf {
            pagerstate.settledPage
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                contentPadding = PaddingValues(horizontal = 65.dp),
                state = pagerstate,
                modifier = Modifier.height(500.dp)
            ) {
                SingleImageCard(it, pagerstate, pagelist[it])
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pagelist[currentitem].title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pagelist[currentitem].descrpition,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 15.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        if (currentitem == pagelist.lastIndex) {
            CustomButton(
                text = "Done",
                onClick = {
                    navigation()
                },
                label = "Done Button"
            )
        }
    }
}