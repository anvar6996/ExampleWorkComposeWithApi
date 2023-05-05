package uz.univer.kattabozortask.domain.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.univer.kattabozortask.data.models.Offer
import uz.univer.kattabozortask.data.remote.OffersApi
import javax.inject.Inject

class OfferRepository @Inject constructor(private val offersApi: OffersApi) {
    suspend fun getCountries(): Result<List<Offer>> = withContext(Dispatchers.IO) {
        kotlin.runCatching { offersApi.getOffers().offers }
    }
}