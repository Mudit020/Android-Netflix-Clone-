package com.mudit20.common.composable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button // From Material 3
import androidx.compose.material3.ButtonDefaults // From Material 3
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mudit20.a35netflixclone.ui.theme.NetflixRed
import com.mudit20.a35netflixclone.ui.theme.NetflixWhite
import com.mudit20.a35netflixclone.ui.theme.NetflixBlack
import com.mudit20.a35netflixclone.ui.theme.NetflixDarkGray
import com.mudit20.a35netflixclone.ui.theme.NetflixLightGray

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    containerColor: Color = NetflixRed,
    contentColor: Color = NetflixWhite,
    disabledContainerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    pressedContainerColor: Color = NetflixBlack,
    pressedContentColor: Color = NetflixWhite,
    disabledButtonColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    disabledButtonTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    enabledButtonColor: Color=NetflixRed,
    enabledButtonTextColor: Color=NetflixWhite,
    isEnable:Boolean=true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()


    val currentContainerColor = when {
        !isEnabled -> disabledContainerColor
        isPressed -> pressedContainerColor
        else -> containerColor
    }


    val currentContentColor = when {
        !isEnabled -> disabledContentColor
        isPressed -> pressedContentColor
        else -> contentColor
    }

    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        interactionSource = interactionSource, // Crucial for collectIsPressedAsState
        colors = ButtonDefaults.buttonColors(
            // These are the standard parameters for ButtonDefaults.buttonColors
            containerColor = currentContainerColor,         // Supply the determined container color
            contentColor = currentContentColor,           // Supply the determined content color
            disabledContainerColor = disabledContainerColor, // This is still needed for the Button's internal logic
            disabledContentColor = disabledContentColor     // This is still needed for the Button's internal logic
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold

        )
    }
}