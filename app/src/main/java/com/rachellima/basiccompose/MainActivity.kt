package com.rachellima.basiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    MessageCard(
                        msg = Message(
                            "Author",
                            "Aprendendo compose no Android"
                        )
                    )
                }
            }
        }
    }
}

data class Message(
    val author: String,
    val body: String
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
                Column { // vertical
                    Text(text = msg.author,
                        color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(10.dp)) // space between items
                    Text(text = msg.body)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message(
            "Rachel",
            "Aprendendo Compose"
        )
    )
}