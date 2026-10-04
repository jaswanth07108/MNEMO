package com.mnemo.app.utils

import com.mnemo.app.domain.model.LocationConfidence
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryMetadata
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

object MemoryEnricher {

    fun enrichUserMemory(
        title: String,
        content: String,
        type: MemoryType,
        userTags: List<String>,
        sourceType: String = "📱 User Memory Capture"
    ): Memory {
        val fullText = "$title $content"
        val lowerText = fullText.lowercase()

        // 1. Sensitivity Check
        val sensitivity = SensitivityDetector.analyzeText(content, title)
        val privacyLevel = if (sensitivity.isSensitive) PrivacyLevel.HIGHLY_SENSITIVE else PrivacyLevel.NORMAL

        // 2. Dynamic Price & Spec Extraction
        val priceMatch = Regex("(₹|rs\\.?|INR)\\s?([0-9,]{3,9})", RegexOption.IGNORE_CASE).find(fullText)
        val priceStr = priceMatch?.value ?: extractPriceFromNumbers(lowerText)
        val priceVal = priceMatch?.groupValues?.get(2)?.replace(",", "")?.toDoubleOrNull()

        val gpuMatch = Regex("(rtx\\s?\\d{4}|gtx\\s?\\d{4}|radeon\\s?\\w+|apple\\s?m\\d)", RegexOption.IGNORE_CASE).find(fullText)?.value
        val ramMatch = Regex("(\\d{1,2}\\s?gb\\s?(ddr\\d|ram)?)", RegexOption.IGNORE_CASE).find(fullText)?.value

        // 3. Metadata Building
        val metadata = if (priceStr != null || gpuMatch != null || ramMatch != null || type == MemoryType.PRODUCT_RESEARCH) {
            MemoryMetadata(
                product = title.ifBlank { "Saved Product" },
                gpu = gpuMatch ?: if (lowerText.contains("laptop")) "Integrated / Graphics" else null,
                ram = ramMatch ?: if (lowerText.contains("laptop")) "8 GB / 16 GB" else null,
                price = priceStr ?: if (lowerText.contains("laptop")) "₹85,000" else null,
                priceValue = priceVal
            )
        } else if (type == MemoryType.DOCUMENT || lowerText.contains("certificate") || lowerText.contains("marks")) {
            MemoryMetadata(
                documentCategory = when {
                    lowerText.contains("degree") -> "Degree Certificate"
                    lowerText.contains("marks") || lowerText.contains("memo") -> "Marks Memorandum"
                    lowerText.contains("internship") -> "Internship Certificate"
                    else -> "Document"
                },
                year = Regex("\\b(20\\d{2}|19\\d{2})\\b").find(fullText)?.value?.toIntOrNull() ?: 2026
            )
        } else {
            null
        }

        // 4. Dynamic Entity & Tag Extraction
        val autoEntities = mutableListOf<String>()
        val autoTags = userTags.map { it.trim().lowercase() }.toMutableList()
        val autoTopics = mutableListOf<String>()

        if (priceStr != null) autoEntities.add("Price: $priceStr")
        if (gpuMatch != null) autoEntities.add("GPU: $gpuMatch")
        if (ramMatch != null) autoEntities.add("RAM: $ramMatch")

        if (lowerText.contains("laptop") || lowerText.contains("gaming")) {
            autoTopics.add("Gaming Laptop Research")
            autoTags.addAll(listOf("laptop", "gaming", "hardware", "tech"))
        }
        if (lowerText.contains("certificate") || lowerText.contains("marks") || lowerText.contains("degree")) {
            autoTopics.add("Education & Certificates")
            autoTags.addAll(listOf("education", "certificate", "marks", "academic"))
        }
        if (lowerText.contains("trip") || lowerText.contains("beach") || lowerText.contains("vacation") || lowerText.contains("hotel")) {
            autoTopics.add("Travel & Trips")
            autoTags.addAll(listOf("travel", "trip", "vacation", "photos"))
        }
        if (lowerText.contains("weather") || lowerText.contains("project") || lowerText.contains("code") || lowerText.contains("iot")) {
            autoTopics.add("Projects & Code")
            autoTags.addAll(listOf("project", "code", "tech"))
        }
        if (autoTopics.isEmpty()) {
            autoTopics.add("Personal Memory")
            autoTags.add("personal")
        }

        // 5. Dynamic Location Deduction
        val location = extractLocation(fullText)

        val currentDateStr = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())
        val isoDateStr = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(Date())

        return Memory(
            id = "user-mem-" + UUID.randomUUID().toString().take(8),
            title = title.ifBlank { "Untitled Memory" },
            content = content,
            type = type,
            privacyLevel = privacyLevel,
            createdAt = isoDateStr,
            capturedAt = isoDateStr,
            formattedDate = currentDateStr,
            sourceType = sourceType,
            extractedText = content,
            location = location,
            metadata = metadata,
            entities = autoEntities.distinct(),
            topics = autoTopics.distinct(),
            tags = autoTags.distinct(),
            isDemoData = false
        )
    }

    private fun extractPriceFromNumbers(text: String): String? {
        val numMatch = Regex("\\b(\\d{5,7}|\\d{2},\\d{3})\\b").find(text)
        return numMatch?.let { "₹" + it.value }
    }

    private fun extractLocation(text: String): LocationConfidence? {
        val lower = text.lowercase()
        val cities = listOf(
            "puducherry" to "Paradise Beach, Puducherry",
            "goa" to "Calangute Beach, Goa",
            "bangalore" to "Bengaluru Tech Park, Karnataka",
            "hyderabad" to "Hitec City, Hyderabad",
            "delhi" to "New Delhi, India",
            "mumbai" to "Marine Drive, Mumbai",
            "coorg" to "Abbey Falls, Coorg",
            "manali" to "Solang Valley, Manali"
        )

        for ((key, name) in cities) {
            if (lower.contains(key)) {
                return LocationConfidence(
                    locationName = name,
                    isHighConfidence = true,
                    evidenceList = listOf("Matched location entity '$key' in text", "User recorded memory context")
                )
            }
        }
        return null
    }
}
