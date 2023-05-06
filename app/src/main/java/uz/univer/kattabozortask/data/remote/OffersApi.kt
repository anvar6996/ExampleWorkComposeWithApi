package uz.univer.kattabozortask.data.remote

import retrofit2.http.GET
import uz.univer.kattabozortask.data.models.OfferResponse

interface OffersApi {
    @GET("offers")
    suspend fun getOffers(): OfferResponse
}