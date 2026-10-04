package com.mnemo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.AmberWarning
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceVariantDark
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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🔐 PRIVACY VAULT", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = CrimsonSensitive)
                }
                Text(
                    text = "Encrypted local storage for Aadhaar, PAN, Passport, Bank, and Credential memories.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Sensitivity Classification Legend
        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Privacy Classification Levels", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "🟢 Normal", fontSize = 11.sp, color = MintEmerald)
                        Text(text = "🔵 Personal", fontSize = 11.sp, color = SoftBlue)
                        Text(text = "🟠 Sensitive", fontSize = 11.sp, color = AmberWarning)
                        Text(text = "🔴 Highly Sensitive", fontSize = 11.sp, color = CrimsonSensitive, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Vault Lock / Unlock Status Box
        if (!isUnlocked) {
            item {
                GlassCard(
                    backgroundColor = SurfaceVariantDark,
                    borderColor = CrimsonSensitive.copy(alpha = 0.5f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.padding(vertical = 12.dp)
                    ) {
                        Text(text = "🔒", fontSize = 42.sp)
                        Text(text = "Privacy Vault is Locked", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                        Text(
                            text = "Biometric authentication or Device PIN is required to inspect sensitive memories.",
                            fontSize = 12.sp,
                            color = TextGrayMuted
                        )
                        Button(
                            onClick = onUnlock,
                            colors = ButtonDefaults.buttonColors(containerColor = CrimsonSensitive),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Unlock with Biometrics 🔓", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextWhite)
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
                    Text(text = "UNLOCKED VAULT MEMORIES (${vaultMemories.size})", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CrimsonSensitive)
                    Button(
                        onClick = onLock,
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceVariantDark),
                        shape = RoundedCornerShape(12.dp)
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
