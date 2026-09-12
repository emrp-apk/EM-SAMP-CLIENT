package com.emrp.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidView(
                factory = { EMSampGameView(it) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun EMSAMPClient() {
    var installing by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0) }
    var status by remember { mutableStateOf("Install the EM-SAMP client files to get started.") }
    var installed by remember { mutableStateOf(false) }

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
                    Image(
                        painter = painterResource(R.drawable.em_samp_logo),
                        contentDescription = "EMRP Logo",
                        modifier = Modifier.size(120.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        "EM-SAMP",
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Empire Mallu Roleplay",
                        fontSize = 21.sp,
                        color = Color.LightGray
                    )

                    Spacer(Modifier.height(18.dp))

                    Text(
                        "play.emrp.online:2026",
                        color = Color(0xFF19E6A1),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }

                Card(
                    modifier = Modifier.width(400.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF10141B)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            if (installed) "READY" else "GAME & CLIENT",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(Modifier.height(14.dp))

                        Text(
                            status,
                            color = Color.Gray,
                            fontSize = 15.sp
                        )

                        if (installing) {
                            Spacer(Modifier.height(20.dp))
                            LinearProgressIndicator(
                                progress = { progress / 100f },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(8.dp))
                            Text("$progress%", color = Color.White)
                        }

                        Spacer(Modifier.height(20.dp))

                        Button(
                            enabled = !installing,
                            onClick = {
                                installing = true
                                progress = 0
                                status = "Preparing download..."

                                // Real download URL will be connected once
                                // the EM-SAMP client package is available.
                                installing = false
                                status = "Client package is not configured yet."
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                if (installed) "PLAY EM-SAMP"
                                else "INSTALL GAME & CLIENT",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
