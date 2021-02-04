package com.segunfrancis.cardinfofinder.domain

data class CardInfoEntity(
    val bank: Bank,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)