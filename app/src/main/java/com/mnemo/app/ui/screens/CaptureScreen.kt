package com.mnemo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceGlass
import com.mnemo.app.ui.theme.SurfaceVariantDark
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun CaptureScreen(
    onSaveMemory: (title: String, content: String, type: MemoryType, tags: List<String>) -> Unit
) {
    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(MemoryType.NOTE) }
    var tagsText by remember { mutableStateOf("general, memory") }
    var isUnderstanding by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = "Capture Memory",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "USER SELECTS → MNEMO REMEMBERS. Explicitly choose what to store.",
                    fontSize = 11.sp,
                    color = MintEmerald
                )
            }
        }

        // Quick Selector Modes
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val modes = listOf(
                    MemoryType.PHOTO to "📷 Photo Picker",
                    MemoryType.DOCUMENT to "📄 SAF Document",
                    MemoryType.VOICE to "🎙 Voice",
                    MemoryType.NOTE to "✍ Note"
                )

                modes.forEach { (type, label) ->
                    val isSel = selectedType == type
                    GlassCard(
                        modifier = Modifier.weight(1f),
                        onClick = { selectedType = type },
                        backgroundColor = if (isSel) ElectricViolet.copy(alpha = 0.3f) else SurfaceVariantDark,
                        borderColor = if (isSel) ElectricViolet else BorderGlass
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            color = TextWhite
                        )
                    }
                }
            }
        }

        // Understanding Memory Indicator (when analyzing)
        if (isUnderstanding) {
            item {
                GlassCard(
                    backgroundColor = SurfaceVariantDark,
                    borderColor = IndigoAccent
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🧠 Understanding your memory...", fontSize = 13.sp, color = IndigoAccent, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Form Fields
        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = "Memory Details", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    OutlinedTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        label = { Text("Memory Title", fontSize = 12.sp) },
                        placeholder = { Text("e.g. ASUS TUF Laptop / B.Tech Certificate", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    OutlinedTextField(
                        value = contentText,
                        onValueChange = { contentText = it },
                        label = { Text("Memory Description / Extracted Text", fontSize = 12.sp) },
                        placeholder = { Text("Describe or paste content. Sensitive information will be detected automatically.", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    OutlinedTextField(
                        value = tagsText,
                        onValueChange = { tagsText = it },
                        label = { Text("Tags (comma separated)", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = {
                            if (titleText.isNotBlank() && contentText.isNotBlank()) {
                                isUnderstanding = true
                                val tagList = tagsText.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                                onSaveMemory(titleText, contentText, selectedType, tagList)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Save Memory", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }
            }
        }
    }
}
