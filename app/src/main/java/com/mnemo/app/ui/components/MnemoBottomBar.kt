package com.mnemo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.BorderGlassActive
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

enum class NavTab {
    HOME,
    MEMORIES,
    CAPTURE,
    ASK,
    VAULT
}

@Composable
fun MnemoBottomBar(
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(CardBackgroundGlass)
            .border(1.dp, BorderGlass, RoundedCornerShape(32.dp))
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tabs = listOf(
                NavTab.HOME to ("🏠" to "Home"),
                NavTab.MEMORIES to ("📂" to "Memories"),
                NavTab.CAPTURE to ("➕" to "Capture"),
                NavTab.ASK to ("✨" to "Ask AI"),
                NavTab.VAULT to ("🔐" to "Vault")
            )

            tabs.forEach { (tab, info) ->
                val isSelected = selectedTab == tab
                val containerColor = if (isSelected) ElectricViolet.copy(alpha = 0.22f) else Color.Transparent
                val borderColor = if (isSelected) BorderGlassActive else Color.Transparent

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(containerColor)
                        .border(1.dp, borderColor, RoundedCornerShape(20.dp))
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = info.first,
                        fontSize = 17.sp
                    )
                    Text(
                        text = info.second,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) TextWhite else TextGrayMuted
                    )
                }
            }
        }
    }
}
