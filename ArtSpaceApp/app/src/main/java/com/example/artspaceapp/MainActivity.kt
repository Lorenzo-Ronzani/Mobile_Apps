package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


data class Artwork(
    val imageRes: Int,
    val title: String,
    val artistAndYear: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    val artworks = listOf(
        Artwork(R.drawable.art1, "Girl with a Pearl Earring", "Johannes Vermeer (1665)"),
        Artwork(R.drawable.art2, "The Persistence of Memory", "Salvador Dalí (1931)"),
        Artwork(R.drawable.art3, "The Triumph of Galatea", "Raphael (1512)")
    )

    var currentIndex by remember { mutableStateOf(0) }
    val current = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtworkWall(
            imageRes = current.imageRes,
            contentDescription = current.title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ArtworkDescriptor(
            title = current.title,
            artistAndYear = current.artistAndYear,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        DisplayController(
            onPrevious = {

                currentIndex = when (currentIndex) {
                    0 -> artworks.lastIndex
                    else -> currentIndex - 1
                }
            },
            onNext = {
                currentIndex = when (currentIndex) {
                    artworks.lastIndex -> 0
                    else -> currentIndex + 1
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ArtworkWall(
    imageRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(shape = RoundedCornerShape(4.dp)) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f)
                )
            }
        }
    }
}

@Composable
fun ArtworkDescriptor(
    title: String,
    artistAndYear: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, shape = RoundedCornerShape(6.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = artistAndYear, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen()
    }
}
