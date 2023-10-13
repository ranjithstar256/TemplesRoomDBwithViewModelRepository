package kp.ran.temples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking
import kp.ran.temples.ui.theme.TemplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TempleDatabase.getInstance(applicationContext)
        val templedao = db.templedao()
        val repository = Repository(templedao)
        val vwmdl = TempleViewModel(repository)




        setContent {
            TemplesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(vwmdl)
                }
            }
        }
    }
}

@Composable
fun Greeting(vwmdl: TempleViewModel) {

    val templeDetails by vwmdl.templeData.observeAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            runBlocking {
                vwmdl.insertStudent(Temple(0, "SriRangam", "Trichy", "LordVishnu"))
            }
        }) {
            Text(text = "Add Temple")
        }

        Button(onClick = {
            runBlocking {
                vwmdl.getAllTempleDetails()
            }
        }) {
            Text(text = "Get All Temples")
        }

        templeDetails?.let {
            LazyColumn(
            ) {
                items(templeDetails!!.size) {
                    Text(text = templeDetails!![it].TempleName)
                }
            }
        } ?: Text(
            text = "No Temple Data Available",
            textAlign = TextAlign.Center,
            fontSize = 16.sp, modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplesTheme {

    }
}