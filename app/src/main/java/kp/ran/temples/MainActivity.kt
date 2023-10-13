package kp.ran.temples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import kp.ran.temples.ui.theme.TemplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db= TempleDatabase.getInstance(applicationContext)
        val templedao = db.templedao()
        val repository= Repository(templedao)
        val vwmdl = TempleViewModel(repository)




        setContent {
            TemplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val temples by vwmdl.temples.observeAsState()
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            runBlocking {
                                vwmdl.insertStudent(Temple(0,"SriRangam","Trichy","LordVishnu"))
                                //templedao.addTemple(Temple(0,"Tirupathi","Andra","Perumal"))
                            }
                        }) {
                            Text(text="Insert the temple")
                        }
                        Divider()
                        Text(text="Temples")
                        LazyColumn(modifier = Modifier
                            .padding(16.dp)
                            ) {
                            items(temples?: listOf()) {
                                Text(text = "temple Name(${it.TempleName}) Location(${it.Location}) Main-God(${it.MainGod})")
                            }
                        }
                    }
                }
            }
        }
    }
}