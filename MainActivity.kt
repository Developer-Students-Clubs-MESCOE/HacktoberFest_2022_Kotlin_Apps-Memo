package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random.Default.nextFloat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //FirstThrusdayTrivia()
            //TextStyling()
            //colorBox()
            //snackBar()
            //scrollImplementation()
            //scrollImplementationByLazy()
            //scrollImplementationByIndexed()
            //constraintLayout()
            SecondThrusdayTrivia()
        }
    }
}

@Composable
fun FirstThrusdayTrivia(){
    val image= painterResource(R.drawable.jetpackcomposeicon)
    val name="Anushree"
    val loveEmoji= painterResource(R.drawable.heartemoji )
    val text="Jetpack Compose"

    Card(modifier = Modifier.fillMaxSize()) {
        Column() {
            Image(painter = image, contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.height(400.dp), alignment = Alignment.Center)
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(text = name, style = TextStyle(fontSize = 16.5.sp),fontWeight = FontWeight.Bold,color=Color(56, 112, 179, 255))
                Spacer(modifier = Modifier.height(15.dp))
                Image(painter = loveEmoji, contentDescription = null, contentScale = ContentScale.Fit,modifier = Modifier.height(50.dp), alignment = Alignment.Center)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = text, style = TextStyle(fontSize = 16.5.sp),fontWeight = FontWeight.Bold, color= Color(8, 48, 66, 255))
            }

        }
        
    }
}

//@Preview
@Composable
fun TextStyling(){
    Text(
        text = buildAnnotatedString {
            withStyle(
                style= SpanStyle(
                    color = Color.Blue,
                    fontSize = 50.sp
                )
            ){
                append("AAAA")
            }
            append("BBBB")
        }, color=Color.Green,
        fontSize =40.sp
    )
}

/*@Composable
fun colorBox(){
    val colo= remember {
        mutableStateOf(Color.Blue)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colo.value)
        .clickable {
            colo.value =Color(Random.nextFloat,Random.nextFloat,Random.nextFloat,1f
            )
        })
}*/


//@Preview(showBackground = true)
@Composable
fun DeafaultPreview(){
    JetpackComposePracticeTheme {
        //colorBox()
    }
}

//@Preview
@Composable
fun snackBar(){
    val scaffoldState= rememberScaffoldState()
    var Textt by remember {
        mutableStateOf(" ")
    }
    val scope= rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        scaffoldState=scaffoldState
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                ){
            TextField(
                value =Textt ,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label ={
                       Text(text =  "Please Enter Text")
                },
                onValueChange ={
                    Textt= it.toString()
                })
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $Textt")
                }
            }) {
                Text(text = "Please Greet Me")
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun scrollImplementation(){
    val scrollState= rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(scrollState),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center) {
        for(i in 1..50){
            Text(text = "ITEM $i", textAlign = TextAlign.Center,
                       fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp))
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun scrollImplementationByLazy(){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)){
        items(50){
            Text(text = "ITEM $it",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp))
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun scrollImplementationByIndexed(){
    LazyColumn(){
        itemsIndexed( listOf("This","is","Anushree")
        ){index, string ->(
                Text(text = string,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp))
                )
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun constraintLayout(){
    val constraints= ConstraintSet {
        val greenBox=createRefFor("greenbox")
        val redBox=createRefFor("redbox")

        constrain(greenBox){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width= Dimension.value(100.dp)
            height= Dimension.value(100.dp)
        }
        constrain(redBox){
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            width= Dimension.value(100.dp)
            height= Dimension.value(100.dp)
        }
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .layoutId("greenbox")
            .background(Color.Green))
        Box(modifier = Modifier
            .fillMaxSize()
            .layoutId("redbox")
            .background(Color.Red))
    }
}


@Preview(showBackground = true)
@Composable
fun SecondThrusdayTrivia(){
    val paint= painterResource(id = R.drawable.rockpaper)
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally,) {

        Image(painter = painterResource(id = R.drawable.rockpaper), contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), contentScale = ContentScale.FillBounds)

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Score", fontSize = 30.sp,textAlign= TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Text(text = "0/4", fontSize = 50.sp,textAlign= TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(80.dp).fillMaxWidth())

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
            verticalAlignment = Alignment.Top
            , horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "You chose", modifier = Modifier.wrapContentWidth())
            Text(text = "Android chose", modifier = Modifier.wrapContentWidth())
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.Top
            , horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "Rock", fontSize = 30.sp, modifier = Modifier.wrapContentWidth(), fontWeight = FontWeight.Bold)
            Text(text = "Paper", fontSize = 30.sp, modifier = Modifier.wrapContentWidth(), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
            verticalAlignment = Alignment.Top
            , horizontalArrangement = Arrangement.SpaceAround) {
            Card(modifier = Modifier
                .width(80.dp)
                .height(80.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {

                Box(modifier = Modifier
                    .background(Color(128, 0, 128))
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Rock", color = Color.White, fontWeight = FontWeight.Bold)
                }

            }

            Card(modifier = Modifier
                .width(80.dp)
                .height(80.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {
                Box(modifier = Modifier
                    .background(Color(128, 0, 128))
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Paper", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Card(modifier = Modifier
                .width(80.dp)
                .height(80.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {
                Box(modifier = Modifier
                    .background(Color(128, 0, 128))
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Scissor", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "#JetpackCompose", textAlign = TextAlign.Center, fontSize =30.sp, modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally))
    }

}