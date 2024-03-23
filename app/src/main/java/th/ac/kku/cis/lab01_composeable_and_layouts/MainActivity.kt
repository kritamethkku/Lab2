package th.ac.kku.cis.lab01_composeable_and_layouts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import th.ac.kku.cis.lab01_composeable_and_layouts.composes.PersonDetail
import th.ac.kku.cis.lab01_composeable_and_layouts.ui.theme.Lab01composeableandlayoutsTheme

data class Person (val name:String, val imageId:Int, val studentId:String)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
            setContent {
            Lab01composeableandlayoutsTheme {
                PersonApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    title: String,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
){
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Navigation"
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen = backStackEntry?.destination?.route ?: "List"
    if(currentScreen.contains("/"))
        currentScreen = currentScreen.split("/")[0]

    val context = LocalContext.current
    Scaffold(
        topBar ={
            MyAppBar(
                title= currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            Button(onClick = {context.startActivity(Intent(context, SecoundActivity::class.java))}) {
                Text(text = "Open SecoundActivity")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "List",
            modifier = Modifier.padding(paddingValues)
        ){
            composable(route = "List"){
                PersonsList(
                    onItemClick = { userId ->
                        navController.navigate(route = "Detail/$userId")},
                    navigateUp = { navController.navigateUp()})
            }
            composable(route = "Detail/{userId}"){
                    backStackEntry -> PersonDetail(navController, userID = backStackEntry.arguments?.getString("userId"))
            }
        }
    }
}
@Composable
fun PersonsList(onItemClick: (String) -> Unit, navigateUp:() -> Unit){
    val persons: List<Person> = listOf<Person>(
        Person("กฤตเมธ มุ้ยกระโทก",R.drawable.krit, "633410005-5"),
        Person("อมาญาวีณ์ สุทธมงคล",R.drawable.yavee, "633410090-8"),
        Person("ศรันย์ ซุ่นเส้ง",R.drawable.saran,"643450086-6"),
        Person("กมล จันบุตรดี",R.drawable.ake,"643450063-8"),
        Person("ประสิทธิชัย จันทร์สม", R.drawable.far, "43450079-3"),
        Person("จักรพรรดิ์ อนุไพร", R.drawable.dew,"643450065-4"),
        Person("ชาญณรงค์ แต่งเมือง", R.drawable.jeb,"643450069-6"),
        Person("สุธาดา โสภาพ", R.drawable.neoy,"643450089-0"),
        Person("อมรรัตน์ มาระเหว", R.drawable.tey,"643450094-7"),
        Person("เทวารัณย์ ชัยกิจ", R.drawable.run,"643450324-6"),
        Person("เจษฎา ลีรัตน์", R.drawable.pat,"643450067-0"),
        Person("ณัฐกานต์ อินทรพานิชย์", R.drawable.way,"643450072-7" ),
        Person("ทัศนีย์ มลาตรี", R.drawable.tuk,"643450075-1"),
        Person("ธนาธิป เตชะ", R.drawable.jay,"643450076-9"),
        Person("ปรเมศวร์ สิทธิมงคล", R.drawable.make, "643450078-5"),
        Person("พรธิตา ขานพล", R.drawable.beam, "643450080-8"),
        Person("พีระเดช โพธิ์หล้า", R.drawable.peach, "643450082-4"),
        Person("วิญญู พรมภิภักดิ์", R.drawable.manu, "643450084-0"),
        Person("ศรสวรรค์ ไพรอนันต์", R.drawable.bow, "643450085-8"),
        Person("อภิทุน ดวงจันทร์คล้อย", R.drawable.pond, "643450092-1"),
        Person("อภิวัฒน์ ดาวโคกสูง", R.drawable.kan, "643450093-9"),
        Person("อรัญ ศรีสวัสดิ์", R.drawable.peaow, "643450095-5"),
        Person("กฤติยา สินโพธิ์", R.drawable.guk, "643450320-4"),
        Person("ก้องภพ ตาดี", R.drawable.model, "643450321-2"),
        Person("เกรียงศักดิ์ หมู่เมืองสอง",R.drawable.singto, "643450322-0"),
        Person("เจษฏา พบสมัย", R.drawable.bank, "643450323-8"),
        Person("ธนบดี สวัสดี", R.drawable.timmy, "643450325-4"),
        Person("นภัสสร ดวงจันทร์", R.drawable.cream, "643450326-2"),
        Person("นภาปิลันธ์ ชาวชายโขง", R.drawable.nat, "643450327-0"),
        Person("นริศรา วงค์บุตรศรี", R.drawable.may, "643450328-8"),
        Person("วรรณภา เบ้านาค", R.drawable.nim, "643450330-1"),
        Person("ศุภชัย แสนประสิทธิ์", R.drawable.beamb, "643450332-7"),
        Person("เสฏฐวุฒิ สาระกุล",R.drawable.sat, "643450333-5" ),
        Person("อฆพร ไร่ขาม", R.drawable.polla, "643450334-3"),
        Person("ปฏิภัทร ดำทองสุก", R.drawable.ink, "643450508-6"),
        Person("กฤตวัฒน์ อินทรสิทธิ์", R.drawable.kf,"643450644-8"),
        Person("ณัฐธิดา บุญพา", R.drawable.jan, "643450647-2"),
        Person("รัตพงษ์ ปานเจริญ", R.drawable.teoy, "643450650-3"),

    )
    val context = LocalContext.current

    Column {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(persons) { persons ->
                PersonListItem(data = persons, onClick = onItemClick)
            }
        }

    }
}
@Composable
fun PersonListItem(data:Person, onClick: (msg: String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            )
            .fillMaxWidth()
            .clickable { onClick(data.studentId) }){
        Image(
            painter = painterResource(id = data.imageId),
            contentDescription = "Avatar image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape))
        Spacer(modifier = Modifier.width(16.dp))
        Column  {
            Text(data.name)
            Text(data.studentId)
        }}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PersonApp()
}