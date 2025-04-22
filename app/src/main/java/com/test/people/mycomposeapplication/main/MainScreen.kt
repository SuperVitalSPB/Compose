import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.people.mycomposeapplication.model.DrawerItem
import com.test.people.mycomposeapplication.model.DrawerItemAbout
import com.test.people.mycomposeapplication.model.DrawerItemContact
import com.test.people.mycomposeapplication.model.DrawerItemHome
import kotlinx.coroutines.launch

const val DEFAULT_STRING = "---"
const val TAG = "@Composable:MainScreen"

@Composable
fun MainScreen(
    draList: List<DrawerItem>,
    textHome: String,
    modifier: Modifier = Modifier) {

    val selectedItem = remember { mutableStateOf(draList[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor =  Color.LightGray
            ) {
                draList.forEach { item ->
                    NavigationDrawerItem(
                        label= { Text(item.caption, fontSize = 22.sp) },
                        selected = selectedItem.value == item,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.LightGray,
                            unselectedContainerColor = Color.Transparent,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.LightGray
                        )
                    )
                }
            }
        },
        content={
            Row{
                IconButton(onClick = {scope.launch {drawerState.open()}},
                    content = { Icon(Icons.Filled.Menu, DEFAULT_STRING) }
                )
                Text(
                    text = "$textHome:${selectedItem.value.caption}",
                    fontSize = 28.sp,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
            Column  (modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize()){
                when (selectedItem.value)
                {
                    is DrawerItemAbout -> AboutScreen(selectedItem.value)
                    is DrawerItemContact -> ContactScreen(selectedItem.value)
                    is DrawerItemHome -> HomeScreen(selectedItem.value)
                }
            }
        }
    )
}
/*
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false},
            title = { Text(text = "Подтверждение действия") },
            text = { Text("Вы действительно хотите ${selectedItem.value}?") },
            confirmButton = {
                Button({ openDialog.value = false
                    textHome.value = "ok clicked"
                    showBottomScheet.value = true
                }) {

                    Text("OK", fontSize = 22.sp)
                    if (showBottomScheet.value) {
                        showBottomScheet.value = false
                        ModalBottomSheetSample()
                    }
                }
            },
            dismissButton = {
                Button({ openDialog.value = false
                    textHome.value = "cancel clicked"
                }) {
                    Text("Cancel", fontSize = 22.sp)
                }
            }
        )
    }
*/
