package uz.univer.kattabozortask.ui.screen.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.univer.kattabozortask.data.models.Offer
import uz.univer.kattabozortask.ui.theme.KattaBozorTaskTheme
@Composable
fun OffersScreen(uiState: State<OffersUIState>, action: (OffersScreenAction) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(25.dp)
                    .drawBehind {
                        drawCircle(
                            Color.Blue, radius = 50f, style = Stroke(5f)
                        )
                    }, color = Color.LightGray, strokeWidth = 2.dp
            )
        } else {
            if (uiState.value.offers.isEmpty()) {
                TryAgain {
                    action(RefreshOffersScreenAction)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items = uiState.value.offers) { offer ->
                        OfferItem(offer = offer)
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(bottom_line_color)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun OfferItem(offer: Offer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        AsyncImage(
            modifier = Modifier.size(85.dp),
            model = ImageRequest.Builder(LocalContext.current).data(offer.image.url).crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(text = offer.name)
            Text(text = offer.category)
            Text(text = offer.brand)
            Text(text = offer.merchant)
        }
    }
}

@Composable
fun TryAgain(onClick: () -> Unit = { }) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Please Try Again")
        Button(onClick = {
            onClick()
        }) {
            Text("Try Again")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OffersScreenPreview() {
    KattaBozorTaskTheme {
        val uiState = remember { mutableStateOf(OffersUIState()) }
        OffersScreen(uiState) {

        }
    }
}
@Preview(showBackground = true)
@Composable
fun TryAgainPreview() {
    TryAgain {}
}

val bottom_line_color = Color(0xffE0E0E0)