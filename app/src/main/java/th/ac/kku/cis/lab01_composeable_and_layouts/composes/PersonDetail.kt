package th.ac.kku.cis.lab01_composeable_and_layouts.composes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PersonDetail(navController: NavHostController = rememberNavController(), userID: String?){
    var displayText: String = "Hello world"
    if(userID != null)
        displayText = userID
    Text(text = displayText)
}