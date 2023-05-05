package uz.univer.kattabozortask.data.models

data class Offer(
    val id: Long,
    val name: String,
    val brand: String,
    val category: String,
    val merchant: String,
    val attributes: List<AttributeOffer>,
    val image: ImageOffer,
)
