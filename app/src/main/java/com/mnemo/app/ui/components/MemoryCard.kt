package com.mnemo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun MemoryCard(
    memory: Memory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (iconEmoji, typeLabel) = when (memory.type) {
        MemoryType.PHOTO -> "📸" to "Photo"
        MemoryType.DOCUMENT -> "📄" to "Document"
        MemoryType.VOICE -> "🎙" to "Audio"
        MemoryType.NOTE -> "✍" to "Note"
        MemoryType.SCREENSHOT -> "🖼" to "Screenshot"
        MemoryType.PRODUCT_RESEARCH -> "💻" to "Spec"
    }

    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        cornerRadius = 24.dp,
        backgroundColor = CardBackgroundGlass,
        borderColor = BorderGlass
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(CyberCyan.copy(alpha = 0.15f))
                            .border(1.dp, CyberCyan.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "$iconEmoji $typeLabel",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = CyberCyan
                        )
                    }

                    if (memory.privacyLevel == PrivacyLevel.HIGHLY_SENSITIVE || memory.privacyLevel == PrivacyLevel.SENSITIVE) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(CrimsonSensitive.copy(alpha = 0.18f))
                                .border(1.dp, CrimsonSensitive.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🔐 VAULT",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = CrimsonSensitive
                            )
                        }
                    }
                }

                Text(
                    text = memory.formattedDate,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextGrayMuted
                )
            }

            // Title
            Text(
                text = memory.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Content Excerpt
            Text(
                text = memory.content,
                fontSize = 13.sp,
                color = TextGrayMuted,
                maxLines = 2,
                lineHeight = 18.sp,
                overflow = TextOverflow.Ellipsis
            )

            // Product Metadata Chip
            memory.metadata?.price?.let { price ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MintEmerald.copy(alpha = 0.12f))
                        .border(1.dp, MintEmerald.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    val gpuText = memory.metadata.gpu?.let { " • GPU: $it" } ?: ""
                    Text(
                        text = "🏷️ $price$gpuText",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MintEmerald
                    )
                }
            }

            // Location Tag
            memory.location?.let { loc ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyberCyan.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = if (loc.isHighConfidence) "📍 EXIF: ${loc.locationName}" else "📍 Visual: ${loc.locationName}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CyberCyan
                    )
                }
            }
        }
    }
}
