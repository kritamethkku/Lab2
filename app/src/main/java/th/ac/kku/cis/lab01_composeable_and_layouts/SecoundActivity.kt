package th.ac.kku.cis.lab01_composeable_and_layouts

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import th.ac.kku.cis.lab01_composeable_and_layouts.ui.theme.Lab01composeableandlayoutsTheme

class SecoundActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01composeableandlayoutsTheme {
                // A surface container using the 'background' color from the theme
                SecoundApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecoundApp() {
    val activity = (LocalContext.current as? Activity)
    Scaffold (
        topBar = { TopAppBar(
            title = { Text("Secound Activity")},
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            navigationIcon = {
                IconButton(onClick = {
                    activity?.finish()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Navigation"
                    )
                }
            }
        )}
    ){ paddingValues ->
        Text(text = "Hello world",
            modifier = Modifier.padding(paddingValues))

    }
}

@Preview(showBackground = true)
@Composable
fun SecoundAppPreview() {
    Lab01composeableandlayoutsTheme {
        SecoundApp()
    }
}