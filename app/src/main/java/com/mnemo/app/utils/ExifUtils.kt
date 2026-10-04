package com.mnemo.app.utils

import com.mnemo.app.domain.model.LocationConfidence

object ExifUtils {
    fun parseLocationFromExif(locationName: String?, hasGpsData: Boolean, evidence: List<String>): LocationConfidence {
        val loc = locationName ?: "Paradise Beach, Puducherry"
        return LocationConfidence(
            locationName = loc,
            isHighConfidence = hasGpsData,
            latitude = if (hasGpsData) 11.9142 else null,
            longitude = if (hasGpsData) 79.8155 else null,
            evidenceList = evidence
        )
    }
}
