package com.mnemo.app.domain.model

import java.util.UUID

enum class MemoryType {
    PHOTO,
    DOCUMENT,
    VOICE,
    NOTE,
    SCREENSHOT,
    PRODUCT_RESEARCH
}

enum class PrivacyLevel {
    NORMAL,
    PERSONAL,
    SENSITIVE,
    HIGHLY_SENSITIVE
}

data class LocationConfidence(
    val locationName: String,
    val isHighConfidence: Boolean,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val evidenceList: List<String> = emptyList()
)

data class MemoryMetadata(
    val product: String? = null,
    val gpu: String? = null,
    val ram: String? = null,
    val price: String? = null,
    val priceValue: Double? = null,
    val currency: String? = "₹",
    val documentCategory: String? = null,
    val year: Int? = null,
    val percentage: String? = null,
    val institution: String? = null,
    val detectedSensitiveFields: List<String> = emptyList()
)

data class Memory(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val type: MemoryType,
    val privacyLevel: PrivacyLevel = PrivacyLevel.NORMAL,
    val createdAt: String,
    val capturedAt: String,
    val formattedDate: String,
    val sourceType: String,
    val localUri: String? = null,
    val thumbnailUri: String? = null,
    val extractedText: String? = null,
    val location: LocationConfidence? = null,
    val metadata: MemoryMetadata? = null,
    val entities: List<String> = emptyList(),
    val topics: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val confidence: Float = 0.95f,
    val relatedMemoryIds: List<String> = emptyList(),
    val isDemoData: Boolean = false
)
