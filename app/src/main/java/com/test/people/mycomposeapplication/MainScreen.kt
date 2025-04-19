import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val items = listOf("Home", "Contact", "About")
    val selectedItem = remember { mutableStateOf(items[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val textHome = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor =  Color.LightGray
            ) {
                items.forEach { item ->
                    NavigationDrawerItem(
                        label= { Text(item, fontSize = 22.sp) },
                        selected = selectedItem.value==item,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Transparent,
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
                    content = { Icon(Icons.Filled.Menu, "Меню") }
                )
                Text(text = textHome.value,
                    fontSize = 28.sp,
                    modifier = Modifier.align ( Alignment.CenterVertically ),)
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
