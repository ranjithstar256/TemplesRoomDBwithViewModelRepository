package kp.ran.temples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.runBlocking
import kp.ran.temples.ui.theme.TemplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db= TempleDatabase.getInstance(applicationContext)
        val templedao = db.templedao()
        val repository= Repository(templedao)
        val vwmdl = TempleViewModel(repository)
        val viewModelFactory = TempleViewModelFactory(repository)
        val vwmdlwithfactory = ViewModelProvider(this, viewModelFactory).get(TempleViewModel::class.java)

/// you directly use vwmdl without using factory also

        runBlocking {

            vwmdl.insertStudent(Temple(0,"Meenakshi","Madurai","LordAmman"))
            //templedao.addTemple(Temple(0,"Tirupathi","Andra","Perumal"))
        }

        setContent {
            TemplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplesTheme {
        Greeting("Android")
    }
}