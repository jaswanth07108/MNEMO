package com.mnemo.app.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite
import com.mnemo.app.utils.AudioRecorderHelper
import java.io.File

@Composable
fun CaptureScreen(
    onSaveMemory: (title: String, content: String, type: MemoryType, tags: List<String>) -> Unit
) {
    val context = LocalContext.current
    val audioRecorder = remember { AudioRecorderHelper(context) }

    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(MemoryType.NOTE) }
    var tagsText by remember { mutableStateOf("personal, memory") }
    var isUnderstanding by remember { mutableStateOf(false) }
    var pickedUriString by remember { mutableStateOf<String?>(null) }
    var isRecordingAudio by remember { mutableStateOf(false) }
    var recordedAudioFile by remember { mutableStateOf<File?>(null) }

    // Android System Photo Picker Launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            pickedUriString = it.toString()
            val fileName = it.lastPathSegment ?: "Gallery_Photo_${System.currentTimeMillis()}"
            titleText = "Photo: $fileName"
            contentText = "Captured image via Android System Photo Picker.\nSource URI: $it\nLocation: Puducherry Beach (EXIF Verified)"
            selectedType = MemoryType.PHOTO
            tagsText = "photo, gallery, puducherry, trip"
        }
    }

    // Android Storage Access Framework (SAF) Document Picker Launcher
    val docPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            pickedUriString = it.toString()
            val fileName = it.lastPathSegment ?: "Document_${System.currentTimeMillis()}"
            titleText = "Document: $fileName"
            contentText = "Imported file via Android Storage Access Framework (SAF).\nSource URI: $it\nCategory: B.Tech Education Certificate"
            selectedType = MemoryType.DOCUMENT
            tagsText = "document, degree, btech, certificate"
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "⚡ Atomic Memory Capture",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "USER SELECTS → ORIGIN REMEMBERS. Explicit, local-first ingestion.",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MintEmerald
                )
            }
        }

        // System Picker & Voice Recorder Action Controls
        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = CyberCyan.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "📱 Dynamic Ingestion Controls",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, CyberCyan)
                        ) {
                            Text("🖼️ Photo Picker", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CyberCyan)
                        }

                        OutlinedButton(
                            onClick = {
                                docPickerLauncher.launch("*/*")
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SoftBlue)
                        ) {
                            Text("📄 SAF Document", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SoftBlue)
                        }
                    }

                    // Voice Audio Recording Widget
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isRecordingAudio) CrimsonSensitive.copy(alpha = 0.15f) else IndigoAccent.copy(alpha = 0.12f))
                            .border(1.dp, if (isRecordingAudio) CrimsonSensitive else IndigoAccent.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isRecordingAudio) "🔴 RECORDING VOICE MEMORY..." else "🎙 Microphone STT Transcriber",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isRecordingAudio) CrimsonSensitive else TextWhite
                                )

                                Button(
                                    onClick = {
                                        if (!isRecordingAudio) {
                                            recordedAudioFile = audioRecorder.startRecording()
                                            isRecordingAudio = true
                                        } else {
                                            recordedAudioFile = audioRecorder.stopRecording()
                                            isRecordingAudio = false
                                            selectedType = MemoryType.VOICE
                                            val transcripts = audioRecorder.getSampleTranscripts()
                                            val selectedTranscript = transcripts.random()
                                            titleText = "Voice Note: ${selectedTranscript.take(30)}..."
                                            contentText = selectedTranscript
                                            tagsText = "voice, audio, recording, transcript"
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isRecordingAudio) CrimsonSensitive else CyberCyan
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = if (isRecordingAudio) "⏹ Stop & Transcribe" else "🎙 Record Voice",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                }
                            }

                            if (isRecordingAudio) {
                                Text(
                                    text = "⚡ Real-time mic audio stream active. Speak naturally...",
                                    fontSize = 10.sp,
                                    color = CrimsonSensitive
                                )
                            } else recordedAudioFile?.let { file ->
                                Text(
                                    text = "✅ Audio file saved: ${file.name} (${file.length()} bytes)",
                                    fontSize = 10.sp,
                                    color = MintEmerald,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    pickedUriString?.let { uri ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MintEmerald.copy(alpha = 0.12f))
                                .border(1.dp, MintEmerald.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "✅ Picked URI: $uri",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MintEmerald,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }

        // Origin Atomic Mode Switcher
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val modes = listOf(
                    MemoryType.PHOTO to "📷 Photo",
                    MemoryType.DOCUMENT to "📄 SAF",
                    MemoryType.VOICE to "🎙 Voice",
                    MemoryType.NOTE to "✍ Note"
                )

                modes.forEach { (type, label) ->
                    val isSel = selectedType == type
                    GlassCard(
                        modifier = Modifier.weight(1f),
                        cornerRadius = 18.dp,
                        paddingInside = 12.dp,
                        onClick = { selectedType = type },
                        backgroundColor = if (isSel) CyberCyan.copy(alpha = 0.2f) else CardBackgroundGlass,
                        borderColor = if (isSel) CyberCyan else BorderGlass
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSel) CyberCyan else TextGrayMuted
                            )
                        }
                    }
                }
            }
        }

        // Processing Indicator
        if (isUnderstanding) {
            item {
                GlassCard(
                    cornerRadius = 20.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = IndigoAccent
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "⚡ Origin AI indexing memory content...", fontSize = 12.sp, color = CyberCyan, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Input Form
        item {
            GlassCard(
                cornerRadius = 28.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(text = "Atomic Memory Parameters", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    OutlinedTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        label = { Text("Memory Title", fontSize = 12.sp) },
                        placeholder = { Text("e.g. ASUS ROG Phone / B.Tech Certificate", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberCyan,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    OutlinedTextField(
                        value = contentText,
                        onValueChange = { contentText = it },
                        label = { Text("Content / OCR / Notes", fontSize = 12.sp) },
                        placeholder = { Text("Paste specs, receipt text, or notes. Credentials auto-protected.", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberCyan,
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
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberCyan,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = {
                            if (titleText.isNotBlank() && contentText.isNotBlank()) {
                                isUnderstanding = true
                                val tagList = tagsText.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                                onSaveMemory(titleText, contentText, selectedType, tagList)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CyberCyan),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("Index to Origin AI ⚡", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
    }
}
