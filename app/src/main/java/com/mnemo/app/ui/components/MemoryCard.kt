package com.mnemo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceVariantDark
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun MemoryCard(
    memory: Memory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconEmoji = when (memory.type) {
        MemoryType.PHOTO -> "📸"
        MemoryType.DOCUMENT -> "📄"
        MemoryType.VOICE -> "🎙"
        MemoryType.NOTE -> "✍"
        MemoryType.SCREENSHOT -> "🖼"
        MemoryType.PRODUCT_RESEARCH -> "💻"
    }

    val privacyBadgeColor = when (memory.privacyLevel) {
        PrivacyLevel.NORMAL -> MintEmerald.copy(alpha = 0.2f)
        PrivacyLevel.PERSONAL -> SoftBlue.copy(alpha = 0.2f)
        PrivacyLevel.SENSITIVE -> AmberWarning.copy(alpha = 0.2f)
        PrivacyLevel.HIGHLY_SENSITIVE -> CrimsonSensitive.copy(alpha = 0.25f)
    }

    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        backgroundColor = SurfaceVariantDark.copy(alpha = 0.7f),
        borderColor = BorderGlass
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Header Row: Type Badge + Source Label + Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ElectricViolet.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "$iconEmoji ${memory.type.name}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricViolet
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    if (memory.privacyLevel == PrivacyLevel.HIGHLY_SENSITIVE || memory.privacyLevel == PrivacyLevel.SENSITIVE) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(privacyBadgeColor)
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🔐 VAULT",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = CrimsonSensitive
                            )
                        }
                    }
                }

                Text(
                    text = memory.formattedDate,
                    fontSize = 11.sp,
                    color = TextGrayMuted
                )
            }

            // Title
            Text(
                text = memory.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Content Snippet
            Text(
                text = memory.content,
                fontSize = 12.sp,
                color = TextGrayMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Extracted Price / Parameter Tag if present
            memory.metadata?.price?.let { price ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MintEmerald.copy(alpha = 0.15f))
                        .border(1.dp, MintEmerald.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Price: $price • ${memory.metadata.gpu ?: ""}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MintEmerald
                    )
                }
            }

            // Location Tag if present
            memory.location?.let { loc ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (loc.isHighConfidence) "📍 ${loc.locationName} (High Conf)" else "📍 ${loc.locationName} (Med Conf)",
                        fontSize = 11.sp,
                        color = SoftBlue,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
