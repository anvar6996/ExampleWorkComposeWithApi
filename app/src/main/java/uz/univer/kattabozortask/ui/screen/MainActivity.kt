package uz.univer.kattabozortask.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.kattabozortask.ui.screen.offers.OffersScreen
import uz.univer.kattabozortask.ui.screen.offers.OffersViewModel
import uz.univer.kattabozortask.ui.theme.KattaBozorTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KattaBozorTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: OffersViewModel = hiltViewModel()
                    val uiState = viewModel.uiState.collectAsState()
                    OffersScreen(uiState,viewModel::execute)
                }
            }
        }
    }
}
