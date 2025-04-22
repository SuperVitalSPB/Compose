import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.people.mycomposeapplication.model.DrawerItem
import kotlinx.coroutines.launch

@Composable
fun ContactScreen(
    mainItem: DrawerItem,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val showBottomScheet = remember { mutableStateOf(false) }
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()){
        IconButton(
            onClick = { showBottomScheet.value = !showBottomScheet.value },
            modifier = modifier,
            content = { Text(mainItem.caption) }
        )
    }
    if (showBottomScheet.value) {
        BottomSheetScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen() {
    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            repeat(5) {
                Text("Item $it", modifier = Modifier.padding(16.dp))
            }
        },
        content = {_ ->
            Text("Content", modifier = Modifier.clickable {
                scope.launch {
                    scaffoldState.bottomSheetState.run {
                        if (!isVisible) show() else hide()
                        // if (isCollapsed) expand() else collapse()
                    }
                }
            })
        }
    )
}