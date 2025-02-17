package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtFrame(
    @DrawableRes painter: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shadowElevation = 16.dp,
        color = colorResource(R.color.white)
    ) {
        Box(
            modifier = Modifier
                .width(400.dp)
                .aspectRatio(12f / 16f)
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(painter),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun DescriptionFrame(
    artworkTitle: String,
    artworkArtist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(400.dp)
            .background(colorResource(R.color.description_frame))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = artworkTitle,
            fontSize = 20.sp,
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = artworkArtist,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = "($year)",
                modifier = Modifier
            )
        }

    }
}

@Composable
fun ButtonFrame(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onPrevious,
            enabled = currentPage > 0,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button)
            ),
        ) {
            Text(stringResource(R.string.prev))
        }
        Spacer(
            modifier = Modifier.weight(1.5f)
        )
        Button(
            modifier = Modifier.weight(1f),
            onClick = onNext,
            enabled = currentPage < ArtSpaceData.items.size - 1,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button)
            ),
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var page by remember { mutableStateOf(0) }

    val currentItem = ArtSpaceData.items[page]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .background(colorResource(R.color.background)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            ArtFrame(
                currentItem.image,
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            DescriptionFrame(
                artworkTitle = currentItem.title,
                artworkArtist = currentItem.name,
                year = currentItem.year,
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            ButtonFrame(
                onPrevious = { if (page > 0) page-- },
                onNext = { if (page < ArtSpaceData.items.size - 1) page++ },
                currentPage = page
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ArtFramePreview() {
    ArtSpaceTheme {
        ArtFrame(
            painter = ArtSpaceData.items[0].image
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionFramePreview() {
    ArtSpaceTheme {
        DescriptionFrame(
            artworkTitle = ArtSpaceData.items[0].title,
            artworkArtist = ArtSpaceData.items[0].name,
            year = ArtSpaceData.items[0].year,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}