import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

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
