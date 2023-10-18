package kp.ran.temples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kp.ran.temples.ui.theme.TemplesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val HiltVM = ViewModelProvider(this).get(TempleViewModel::class.java)

        setContent {
            TemplesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        GetAllTemple(HiltVM)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllTemple(vwmdl: TempleViewModel) {

    val scope = rememberCoroutineScope()

    Column() {

        var templeName by remember { mutableStateOf("") }
        var templeLocation by remember { mutableStateOf("") }
        var MainGod by remember { mutableStateOf("") }

        OutlinedTextField(
            value = templeName,
            onValueChange = { templeName = it },
            placeholder = { Text(text = "temple name") })
        OutlinedTextField(
            value = templeLocation,
            onValueChange = { templeLocation = it },
            placeholder = { Text(text = "temple Location ") })
        OutlinedTextField(
            value = MainGod,
            onValueChange = { MainGod = it },
            placeholder = { Text(text = "MainGod name") })

        Button(onClick = {
            scope.launch {
                vwmdl.insertStudent(
                    Temple(
                        0,
                        templeName,
                        templeLocation,
                        MainGod
                    )
                )
                templeName = ""
                templeLocation = ""
                MainGod = ""
            }
        }) {
            Text(text = "add Temple")
        }

        val temples by vwmdl.allTemples.observeAsState(emptyList())
        Column(Modifier.verticalScroll(rememberScrollState())) {
            temples.forEach { temple ->
                Text(text = "${temple.TempleName} - ${temple.Location} - ${temple.MainGod} ")
            }
        }
    }
}

