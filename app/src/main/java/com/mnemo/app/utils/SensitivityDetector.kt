package com.mnemo.app.utils

import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.domain.model.SensitiveDetectionResult

object SensitivityDetector {

    fun analyzeText(text: String, title: String = ""): SensitiveDetectionResult {
        val combined = "$title $text".lowercase()
        val detectedFields = mutableListOf<String>()

        if (combined.contains("aadhaar") || combined.contains("uidai") || Regex("\\b\\d{4}\\s?\\d{4}\\s?\\d{4}\\b").containsMatchIn(text)) {
            detectedFields.add("Aadhaar Number / Government ID")
        }
        if (combined.contains("pan card") || Regex("[A-Z]{5}[0-9]{4}[A-Z]{1}").containsMatchIn(text)) {
            detectedFields.add("PAN Number / Tax Identifier")
        }
        if (combined.contains("passport") || combined.contains("republic of india")) {
            detectedFields.add("Passport Details")
        }
        if (combined.contains("bank") || combined.contains("account number") || combined.contains("ifsc") || combined.contains("cvv") || combined.contains("credit card")) {
            detectedFields.add("Bank / Financial Credentials")
        }
        if (combined.contains("password") || combined.contains("otp") || combined.contains("pin") || combined.contains("secret key")) {
            detectedFields.add("Authentication Secret / Password")
        }

        return if (detectedFields.isNotEmpty()) {
            SensitiveDetectionResult(
                isSensitive = true,
                detectedType = detectedFields.first(),
                detectedFields = detectedFields,
                recommendedPrivacy = PrivacyLevel.HIGHLY_SENSITIVE,
                warningMessage = "⚠️ Sensitive Information Detected: ${detectedFields.joinToString(", ")}"
            )
        } else {
            SensitiveDetectionResult(
                isSensitive = false,
                recommendedPrivacy = PrivacyLevel.NORMAL
            )
        }
    }
}
