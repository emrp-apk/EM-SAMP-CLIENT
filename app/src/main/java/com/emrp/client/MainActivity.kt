package com.emrp.client

import android.os.Bundle
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EMSAMPClient() }
    }
}

@Composable
fun EMSAMPClient() {
    var gameReady by remember { mutableStateOf(false) }
    var gameFolder by remember { mutableStateOf<String?>(null) }

    val folderPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        if (uri != null) {
            gameFolder = uri.toString()
            gameReady = true
        }
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF07090D),
            surface = Color(0xFF10141B),
            primary = Color(0xFF19E6A1),
            secondary = Color(0xFF25A7FF)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "EM-SAMP",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Text(
                        "Empire Mallu Roleplay",
                        fontSize = 20.sp,
                        color = Color.LightGray
                    )

                    Spacer(Modifier.height(18.dp))

                    Text(
                        "play.emrp.online:2026",
                        color = Color(0xFF19E6A1),
                        fontWeight = FontWeight.Bold
                    )
                }

                Card(
                    modifier = Modifier.width(380.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF10141B)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            if (gameReady) "GAME READY" else "GAME FILES",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            if (gameReady)
                                "Your game files are ready."
                            else
                                "Select your legally obtained GTA: San Andreas game files to continue.",
                            color = Color.Gray
                        )

                        Spacer(Modifier.height(20.dp))

                        Button(
                            onClick = { folderPicker.launch(null) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                if (gameReady) "PLAY EM-SAMP"
                                else "SELECT GAME FILES"
                            )
                        }
                    }
                }
            }
        }
    }
}
