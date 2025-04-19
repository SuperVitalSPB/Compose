import android.R
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.alpha
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.people.mycomposeapplication.HomeViewModel

@Composable
fun HomeScreenViewModel12(
    homeViewModel: HomeViewModel = viewModel()
) {
    // val counter by homeViewModel.counter.collectAsState()
    // val enabled by homeViewModel.enabled.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()

    ClickCounter(
        count = uiState.count, // counter,
        onCounterClick = homeViewModel::onCounterClick
    )
    EnableFeature(
        enabled = uiState.enabled, // enabled,
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
            .padding(top = 80.dp, start = 16.dp)
    )
}

@Composable
fun EnableFeature(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = CenterVertically,
        modifier = Modifier.padding(top = 85.dp)) {
        Checkbox(checked = enabled, onCheckedChange = onEnabledChange)
        AnimatedVisibility(visible = enabled) { Text("feature $enabled") }
        //AnimationExample(enabled)
    }
}

@Composable
fun AnimationExample(loading: Boolean) {
    val alpha: Float by animateFloatAsState(
        targetValue = if (loading) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing,
        ),
        label = "alpha"
    )
    Text(
        text = "feature $loading",
        modifier = Modifier.alpha(alpha),
    )
}

data class HomeScreenUiState(
    val count: Int = 0,
    val enabled: Boolean = false
)

@Composable
fun HomeScreenLazyColumn11() {
    Log.d(TAG, "HomeScreen")
    val list = remember {
        List(20) { "Item ${it+1}"}.toMutableStateList()
    }
    // LazyColumn(modifier = Modifier.padding(top = 80.dp))
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .border(width = 2.dp, color = Color.Green)
    )
    {
        items(list) { item ->
            SomeItem(text = item)
        }
    }
}
const val TAG = "HomeScreen"

@Composable
fun HomeScreenMain() {
    Log.d(TAG, "HomeScreen")
    val list = remember {
        List(20) { "Item ${it+1}"}.toMutableStateList()
    }

    Column (modifier = Modifier.padding(top = 80.dp)
        .verticalScroll(rememberScrollState())){
        TextButton(onClick = {
            Log.d(TAG, "--- insert ---")
            list.add(0, "Item ${list.size + 1}")
        }) {
            Text(text = "Insert")
        }
        list.forEach { value ->
            key(value) {
                SomeItem(value)
            }
        }
    }
}

@Composable
fun SomeItem(text: String) {
    Log.d(TAG, "SomeItem $text")
    Text(
        text = text, fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(16.dp)
    )
}

@Composable
fun HomeScreenCheck() {
    // val checked = remember { mutableStateOf(false) }
    var checked by rememberSaveable { mutableStateOf(false) }
    val checkedValue = checked
    Column {
        Row(modifier = Modifier.padding(top = 50.dp), verticalAlignment = CenterVertically) {
            Checkbox(checked = checkedValue, onCheckedChange = { value -> checked = value })
            Text("More details",
                fontSize = 18.sp,
                modifier = Modifier.clickable(onClick = { checked = !checkedValue }),)
        }
        if (checkedValue) {
            Text(text =  "too tools:sample/lorem/random")
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ClickCounterxx(
    counterValue: Int,
    onCounterClick: () -> Unit
) {
    val evenOdd = remember { EvenOdd(uppercase = true) }
    Text(
        text = "Clicks: $counterValue ${evenOdd.check(counterValue)}",
        modifier = Modifier.clickable(onClick = onCounterClick)
            .padding(top = 50.dp)
    )
    Log.d(TAG, "ClickCounter $counterValue ${evenOdd.hashCode().toHexString()}")
}

@Composable
fun HomeScreen(
    text: State<String>,
    onValueChange: (String) -> Unit
) {
    val textValue = text.value
    OutlinedTextField(modifier = Modifier.padding(top = 50.dp),value = textValue, onValueChange = onValueChange)
}

@Composable
fun HomeScreen(
    checked: State<Boolean>,
    onCheckedChange: (Boolean) -> Unit,
    a: Int
) {
    val checkedValue = checked.value
    Row(modifier = Modifier.padding(top = 50.dp),
        verticalAlignment = CenterVertically) {
        Checkbox(checked = checkedValue, onCheckedChange = onCheckedChange)
        Text(
            "Some checkbox text",
            Modifier.clickable(onClick = { onCheckedChange(!checkedValue) }),
            fontSize = 18.sp)
    }
}

@Composable
fun HomeScreen(
    counter: State<Int>,
    onCounterClick: () -> Unit
) {
    val counterValue = counter.value
    Text(
        text = "Clicks: $counterValue",
        modifier = Modifier
            .clickable(onClick = onCounterClick)
            .padding(top = 100.dp)
    )
}

@Composable
fun HomeScreenImage() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_media_ff),
            contentDescription = null
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_secure),
            contentDescription = null
        )
    }
}

@Composable
fun HomeScreen(list: List<String>) {
    if (list.isEmpty()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Empty screen")
        }
    } else {
        Column (modifier = Modifier.padding(start = 150.dp, top = 70.dp)){
            for (s in list) {
                Text(text = s)
            }
        }
    }
}

@Composable
fun HomeScreen(double: Double) {
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = "https://developer.android.com/images/android-go/next-billion-users_856.png",
        contentDescription = null
    )
}

@Composable
fun HomeScreen(a:Int) {
    Row(verticalAlignment = CenterVertically,
        modifier = Modifier.padding(start = 20.dp, top = 70.dp)) {
        Box {
            Text("N", fontSize = 48.sp)
            Text("ame", modifier = Modifier.align(BottomCenter))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Title")
            Text("Description")
        }
    }
/*    Box (Modifier.padding(top = 50.dp)){
        Text(text = "N", fontSize = 48.sp,
            modifier = Modifier.background(color = Color.Green)
        )
        Text(text = "ame",
            modifier = Modifier.background(color = Color.Yellow)
                .align(BottomCenter)
        )
    }*/
/*    Row(verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 80.dp)) {
        Text(text = "Name", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Surname", fontSize = 20.sp)
    }*/
/*    Column(
        modifier = Modifier.fillMaxSize()
                .padding(80.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "Title", fontSize = 32.sp)
        Text(text = "Description", fontSize = 20.sp,
            modifier = Modifier.align(alignment = Alignment.Start)
        )
    }*/
/*    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(top = 80.dp)
            .background(color = Color.Red)) {
        Text(text = "Title", fontSize = 32.sp)
        Text(text = "Description", fontSize = 20.sp)

        Text(
            "Home screen",
            fontSize = 14.sp,
            color = Color.Green,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(color = Color.Gray)
                //.width(60.dp)
                .fillMaxWidth()
                .padding(top = 80.dp)
        )
        Text("123456789")
    }*/
}

class EvenOdd(private val uppercase: Boolean) {
    fun check(value: Int): String {
        var result = if (value % 2 == 0) "even" else "odd"
        if (uppercase) result = result.uppercase()
        return result
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString(): String {
        return "EvenOdd(uppercase = $uppercase, hashcode = ${hashCode().toHexString()})"
    }
}