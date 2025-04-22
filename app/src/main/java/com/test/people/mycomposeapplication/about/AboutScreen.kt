import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.test.people.mycomposeapplication.model.DrawerItem

@Composable
fun AboutScreen(
    mainItem: DrawerItem,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    /*Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()){
        IconButton(
            onClick = {},
            modifier = modifier,
            content = { Text(mainItem.caption) }
        )
    }*/
    Box(
        modifier = Modifier.background(Color.LightGray)
    ) {
        Text(text = "Some text")
    }
}