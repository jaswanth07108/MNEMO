package com.mnemo.app.domain.model

data class SensitiveDetectionResult(
    val isSensitive: Boolean,
    val detectedType: String? = null, // e.g. "Government ID", "Financial Credentials", "API Key"
    val detectedFields: List<String> = emptyList(),
    val recommendedPrivacy: PrivacyLevel = PrivacyLevel.NORMAL,
    val warningMessage: String? = null
)
