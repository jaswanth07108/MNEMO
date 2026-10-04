package com.mnemo.app.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun VaultScreen(
    isUnlocked: Boolean,
    vaultMemories: List<Memory>,
    onUnlock: () -> Unit,
    onLock: () -> Unit,
    onMemoryClick: (Memory) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Origin Atomic Vault Header
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(CrimsonSensitive.copy(alpha = 0.15f))
                            .border(1.dp, CrimsonSensitive.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(text = "🔐 ORIGIN ATOMIC VAULT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CrimsonSensitive, letterSpacing = 0.5.sp)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Encrypted on-device storage for IDs, credentials & sensitive data.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Sensitivity Classification
        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(text = "Origin Sensitivity Matrix", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "🟢 Normal", fontSize = 11.sp, color = MintEmerald)
                        Text(text = "🔵 Personal", fontSize = 11.sp, color = SoftBlue)
                        Text(text = "🟠 Sensitive", fontSize = 11.sp, color = AmberWarning)
                        Text(text = "🔴 Critical", fontSize = 11.sp, color = CrimsonSensitive, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Vault Lock / Unlock State
        if (!isUnlocked) {
            item {
                GlassCard(
                    cornerRadius = 28.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = CrimsonSensitive.copy(alpha = 0.5f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    ) {
                        Text(text = "🛡️", fontSize = 52.sp)
                        Text(text = "Atomic Vault Encrypted", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                        Text(
                            text = "Biometric authentication required to access sensitive evidence.",
                            fontSize = 12.sp,
                            color = TextGrayMuted
                        )
                        Button(
                            onClick = onUnlock,
                            colors = ButtonDefaults.buttonColors(containerColor = CrimsonSensitive),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Authenticate & Unlock 🔓", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextWhite)
                        }
                    }
                }
            }
        } else {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "UNLOCKED VAULT (${vaultMemories.size})", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CrimsonSensitive, letterSpacing = 1.5.sp)
                    Button(
                        onClick = onLock,
                        colors = ButtonDefaults.buttonColors(containerColor = CardBackgroundGlass),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Lock Vault 🔒", fontSize = 11.sp, color = TextWhite)
                    }
                }
            }

            items(vaultMemories) { memoryItem ->
                MemoryCard(
                    memory = memoryItem,
                    onClick = { onMemoryClick(memoryItem) }
                )
            }
        }
    }
}
