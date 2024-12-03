package com.example.weatherappcompose.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.AccentBlue
import com.example.weatherappcompose.ui.theme.BaseWhite
import kotlinx.coroutines.launch

@Composable
fun MainCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = AccentBlue
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "27.11.2024",
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.White
                )
                AsyncImage(
                    model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                    contentDescription = "weatherIcon",
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = "Irkutsk",
                style = TextStyle(fontSize = 32.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "23°C",
                style = TextStyle(fontSize = 40.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "sunny",
                style = TextStyle(fontSize = 15.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "23.0 °C - 8.0 °C",
                style = TextStyle(fontSize = 15.sp),
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "iconSearch",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_refresh),
                        contentDescription = "iconSearch",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {
    val tabList = listOf("Часы", "Дни")
    val pagerState = rememberPagerState(pageCount = {
        tabList.size
    })
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(top = 8.dp),
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions: List<TabPosition> ->
                Box {}
            },
            containerColor = Color(0xff1E76DA),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(50))
                .padding(1.dp),
        ) {
            tabList.forEachIndexed { index, text ->
                val selected = tabIndex == index
                Tab(
                    selected = selected,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (selected) Color.White else Color(0xff1E76DA)),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text,
                            color = Color(0xff6FAAEE)
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> DayPage("Вкладка 1")
                1 -> DayPage("Вкладка 2")
            }
        }
    }
}

@Composable
fun DayPage(title: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = AccentBlue
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column() {
            Text(
                color = BaseWhite,
                text = title
            )
        }
    }
}