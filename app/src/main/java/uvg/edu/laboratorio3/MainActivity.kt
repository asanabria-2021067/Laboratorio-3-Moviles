package uvg.edu.laboratorio3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.edu.laboratorio3.R
import uvg.edu.laboratorio3.ui.theme.Laboratorio3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaskApp { msg ->
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun TaskApp(onError: (String) -> Unit) {
    var taskText by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(listOf<String>()) }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.imagen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.app_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(stringResource(R.string.hint_task)) },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (taskText.isNotBlank()) {
                            taskList = taskList + taskText.trim()
                            taskText = ""
                        }
                    }
                ) {
                    Text(stringResource(R.string.btn_add))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(taskList) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.9f)
                        )
                    ) {
                        Text(
                            text = task,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
