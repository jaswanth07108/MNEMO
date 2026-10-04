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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mnemo.app.domain.model.SensitiveDetectionResult
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.SurfaceDark
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun SensitivityDialog(
    result: SensitiveDetectionResult,
    onSaveSecurely: () -> Unit,
    onDontSave: () -> Unit
) {
    Dialog(onDismissRequest = onDontSave) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceDark)
                .border(1.5.dp, CrimsonSensitive, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⚠️", fontSize = 28.sp)
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Column {
                        Text(
                            text = "Sensitive Information Detected",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CrimsonSensitive
                        )
                        Text(
                            text = "Privacy Vault Protection Active",
                            fontSize = 11.sp,
                            color = TextGrayMuted
                        )
                    }
                }

                Text(
                    text = "MNEMO detected highly sensitive personal credentials in this memory:",
                    fontSize = 13.sp,
                    color = TextWhite
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.4f))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    result.detectedFields.forEach { field ->
                        Text(
                            text = "🔴 $field",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AmberWarning
                        )
                    }
                }

                Text(
                    text = "Would you like to encrypt and save this memory securely inside your Biometric Privacy Vault?",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onDontSave,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Don't Save", fontSize = 12.sp, color = TextGrayMuted)
                    }

                    Button(
                        onClick = onSaveSecurely,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = CrimsonSensitive),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Save Securely", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }
            }
        }
    }
}
