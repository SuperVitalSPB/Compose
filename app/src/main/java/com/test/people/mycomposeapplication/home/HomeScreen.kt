import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.people.mycomposeapplication.home.HomeViewModel
import com.test.people.mycomposeapplication.model.DrawerItem
import com.test.people.mycomposeapplication.model.HomeScreenUiState

@Composable
fun HomeScreen(
    mainItem: DrawerItem,
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        mainItem = mainItem,
        uiState = uiState,
        onCounterClick = homeViewModel::onCounterClick,
        onEnabledChange = homeViewModel::setEnabled
    )
}

@Composable
fun ClickCounter(
    count: Int,
    onCounterClick: () -> Unit
) {
    Text(
        text = "Clicks: $count",
        modifier = Modifier.clickable(onClick = onCounterClick)
    )
}

@Composable
fun EnableFeature(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = CenterVertically,
        modifier = Modifier.background(color = Color.Green)) {
        Checkbox(checked = enabled,
            onCheckedChange = onEnabledChange,
            modifier = Modifier.background(
                color = if (enabled) Color.Blue
            else Color.Gray))
        Text("enable feature", modifier = Modifier.background(color = Color.Red))
    }
}

@Composable
fun HomeScreen(
    mainItem: DrawerItem,
    modifier: Modifier = Modifier.fillMaxSize(),
    uiState: HomeScreenUiState,
    onCounterClick: () -> Unit,
    onEnabledChange: (Boolean) -> Unit
) {
    Column (modifier = Modifier.padding(start = 16.dp)) {
        Text("caption: ${mainItem.caption}")

        ClickCounter(
            count = uiState.count,
            onCounterClick = onCounterClick
        )
    }
    EnableFeature(
        enabled = uiState.enabled,
        onEnabledChange = onEnabledChange
    )
    Column (modifier = Modifier.padding(start = 16.dp)) {
        Button(onClick = {  }) {
            Text("Some text")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null
            )
        }
    }

}