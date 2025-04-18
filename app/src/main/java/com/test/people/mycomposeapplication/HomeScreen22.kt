import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDrawer() {
    val items = listOf("Home", "Contact", "About")
    val selectedItem = remember { mutableStateOf(items[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val textHome = remember { mutableStateOf(items[0]) }
    val showBottomScheet = remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        modifier = Modifier.padding(top = 40.dp),
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
                            openDialog.value = true
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetSample() {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    // App content
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            Modifier.toggleable(
                value = skipPartiallyExpanded,
                role = Role.Checkbox,
                onValueChange = { checked -> skipPartiallyExpanded = checked }
            )
        ) {
            Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Skip partially expanded State")
        }
        Button(
            onClick = { openBottomSheet = !openBottomSheet },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Show Bottom Sheet")
        }
    }

    // Sheet content
    if (openBottomSheet) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                    // you must additionally handle intended state cleanup, if any.
                    onClick = {
                        scope
                            .launch { bottomSheetState.hide() }
                            .invokeOnCompletion {
                                if (!bottomSheetState.isVisible) {
                                    openBottomSheet = false
                                }
                            }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
            }
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.padding(horizontal = 16.dp),
                label = { Text("Text field") }
            )
            LazyColumn {
                items(25) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        },
                        colors =
                            ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                            ),
                    )
                }
            }
        }
    }
}


@Composable
fun SchowSnackbar() {
    Snackbar{
        Text("Загрузка завершена", fontSize = 22.sp)
    }
}

@Composable
fun HomeScreenLesson22() {
    val isAdded = remember { mutableStateOf(false) }
    val isShowSnackbar = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title= { Text("METANIT.COM", fontSize = 22.sp)},
                navigationIcon={ IconButton({ }) { Icon(Icons.Filled.Menu, contentDescription = "Меню")}},
                actions={
                    IconButton({
                        isAdded.value = !isAdded.value
                    }) { Icon(Icons.Filled.Info, contentDescription = "О приложении")}
                    IconButton(onClick = {
                        isShowSnackbar.value = !isShowSnackbar.value
                    }) {Icon(Icons.Filled.Search, contentDescription = "Поиск")}
                },
                colors= TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    navigationIconContentColor = Color.LightGray,
                    actionIconContentColor = Color.LightGray))
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray,
                contentColor = Color.LightGray
            ){
                IconButton(onClick = {  }) { Icon(Icons.Filled.Favorite, contentDescription = "Избранное")}
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = {  }) { Icon(Icons.Filled.Info, contentDescription = "Справка")}
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    if(isAdded.value) Icon(Icons.Filled.Clear, contentDescription = "Удалить")
                    else Icon(Icons.Filled.Add, contentDescription = "Добавить") },
                onClick = { isAdded.value = !isAdded.value}
            )
        },
        snackbarHost = {
            if (isShowSnackbar.value) {
                Snackbar {
                    Text("Загрузка завершена", fontSize = 22.sp)
                }
            }
        }

    ){
        Text("Hello METANIT.COM", fontSize = 28.sp, modifier = Modifier.padding(it))
    }

}
