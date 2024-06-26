package com.rachellima.basiccompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rachellima.basiccompose.ui.theme.BasicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicComposeTheme {
                Surface(Modifier.fillMaxSize()) {
                    BasicComposeTheme {
                        Conversation(messages = SampleData.conversationSample)
                    }
                }
            }
        }
    }
}

data class Message(
    val author: String, val body: String
)

@Composable
fun MessageCard(msg: Message) {
    BasicComposeTheme {
        Surface {
            Row(modifier = Modifier.padding(all = 18.dp)) { // horizontal
                Image( // added image
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "Foto para Contato",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    label = "change color"
                )

                Column(modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }
                ) { // vertical
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(10.dp)) // space between items
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 1.dp,
                        color = surfaceColor,
                        modifier = Modifier.animateContentSize().padding(4.dp)
                    ) {
                        Text(
                            text = msg.body,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message(
            "Rachel", "Aprendendo Compose"
        )
    )
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    BasicComposeTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}