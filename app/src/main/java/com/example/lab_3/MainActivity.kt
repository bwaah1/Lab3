package com.example.lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.lab_3.ui.theme.TouristAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    TouristAppNavigation(navController)
                }
            }
        }
    }
}

@Composable
fun TouristAppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") { DestinationListScreen(navController) }
        composable(
            "details/{destinationName}/{details}",
            arguments = listOf(
                navArgument("destinationName") { type = NavType.StringType },
                navArgument("details") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val destinationName = backStackEntry.arguments?.getString("destinationName") ?: "Невідоме місце"
            val details = backStackEntry.arguments?.getString("details") ?: "Опис відсутній"
            DestinationDetailsScreen(destinationName, details, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDetailsScreen(destinationName: String, details: String, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        destinationName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Назад",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Text(
                text = details,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Популярні напрямки",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(destinations) { destination ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                "details/${destination.name}/${destination.details}"
                            )
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = destination.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

val destinations = listOf(
    Destination(
        name = "Замок Бран, Румунія",
        details = "Середньовічний замок в Трансільванії, відомий як 'Замок Дракули'. Побудований у 14 столітті на вершині скелі."
    ),
    Destination(
        name = "Катакомби Парижа, Франція",
        details = "Підземний лабіринт довжиною 300 км. Містить останки більше 6 мільйонів людей. Створений у 18 столітті."
    ),
    Destination(
        name = "Единбурзький замок, Шотландія",
        details = "Величний замок на вершині згаслого вулкана. Відомий своїми підземеллями та привидами королівської сім'ї."
    ),
    Destination(
        name = "Склеп Капуцинів, Відень",
        details = "Імператорський склеп династії Габсбургів. Містить 107 саркофагів, прикрашених в стилі бароко та рококо."
    ),
    Destination(
        name = "Замок Гоуска, Чехія",
        details = "Замок, побудований над так званими 'Воротами в Пекло'. За легендами, приховує портал в інший вимір."
    )
)


data class Destination(val name: String, val details: String)