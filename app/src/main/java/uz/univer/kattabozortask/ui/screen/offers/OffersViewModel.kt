package uz.univer.kattabozortask.ui.screen.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.univer.kattabozortask.data.models.Offer
import uz.univer.kattabozortask.domain.repositories.OfferRepository
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    private val offerRepository: OfferRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OffersUIState())
    val uiState = _uiState.asStateFlow()

    init {
        execute()
    }

    fun execute(action: OffersScreenAction) {
        when (action) {
            is RefreshOffersScreenAction -> execute()
        }
    }

    private fun execute() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            offerRepository.getCountries().apply {
                onSuccess { offers ->
                    _uiState.update {
                        it.copy(isLoading = false, message = null, offers = offers)
                    }
                }
                onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, message = error.message) }
                }
            }
        }
    }
}

sealed interface OffersScreenAction
object RefreshOffersScreenAction : OffersScreenAction

data class OffersUIState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val offers: List<Offer> = emptyList()
)