package com.hftamayo.onduties.core.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hftamayo.onduties.core.util.ContentDescriptions

@Composable
fun CompleteButtons(
    onCompleteClick: () -> Unit,
    color: Color,
    isComplete: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = {
        onCompleteClick()
    }, modifier = modifier) {
        if (isComplete) {
            Icon(
                imageVector = Icons.Default.CheckCircleOutline,
                contentDescription = ContentDescriptions.COMPLETE_TODO_ITEM,
                tint = color,
                modifier = Modifier.size(48.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Mark as complete",
                tint = color
            )
        }
    }

}

@Composable

fun EmptyCircle(color: Color, strokeWith: Float = 9f) {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawCircle(
                color = color,
                radius = size.minDimension / 2,
                center = size.center,
                style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWith)
            )
        }
    )
}