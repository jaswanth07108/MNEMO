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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.ComparisonRow
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceVariantDark
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun CompareScreen(
    rows: List<ComparisonRow>,
    onBack: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceVariantDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("← Back to Results", fontSize = 12.sp, color = TextWhite)
                }

                Text("📊 Side-by-Side Comparison", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ElectricViolet)
            }
        }

        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Retrieved Evidence Comparison", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    Text("Extracted parameters from your saved screenshots and product research notes.", fontSize = 11.sp, color = TextGrayMuted)
                }
            }
        }

        items(rows) { row ->
            GlassCard(
                backgroundColor = SurfaceVariantDark.copy(alpha = 0.8f),
                borderColor = if (row.model.contains("ASUS")) MintEmerald.copy(alpha = 0.5f) else BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(row.model, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                        Text(row.price, fontSize = 16.sp, fontWeight = FontWeight.Black, color = MintEmerald)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(ElectricViolet.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(row.gpu, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ElectricViolet)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SoftBlue.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(row.ram, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SoftBlue)
                        }
                    }

                    Text("Rationale: ${row.rationale}", fontSize = 12.sp, color = TextGrayMuted)
                }
            }
        }
    }
}
