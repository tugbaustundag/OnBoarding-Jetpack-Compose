package com.smality.jetpackcomposeonboarding.onBoarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun OnBoarding() {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        //Top alanını oluşturan compose çağırıyoruz
        TopSection()
        //OnBoardingData sınıfından OnBoarding ekran sayısını alıyoruz
        val item = getData()
        val state = rememberPagerState(pageCount = item.size)
        //OnBoardingItem'a item resim ve yazıları arayüz elementlerine aktarmasını
        //sağlıyoruz
        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->
            OnBoardingItem(item = item[page])
        }
        //Ekran sayısını BottomSection compose da kullanarak pager ve scrool işlemi
        BottomSection(size = item.size, index = state.currentPage) {
            if (state.currentPage+1 < item.size)
                scope.launch {
                    state.scrollToPage(page = state.currentPage+1)
                }
        }
    }
}

//Top alanını oluşturan compose
@Composable
@Preview
fun TopSection() {
    //Padding 12dp olan kutu oluşturuyoruz
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) { //Sol köşedeki ok simgeli butonu oluturma
        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            //iconu atama
            Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
        }
        //Skip adlı text buttonunu oluşturma
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = "Skip",
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
//Pager görselini ekran sayısı kadar çoğaltan compose
@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }

    }

}
//Bottom alanındaki FloatingActionButton ve Pager göstergesini oluşturma
@Composable
fun BottomSection(size: Int, index: Int, onNextClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {//Pager bölümünü oluşturan compose çağırıyoruz
        Indicators(size = size, index = index)
        //Sağdaki FloatingActionButton rengini, iconunu tanımalama
        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(Alignment.CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }
    }
}
//Bir tane Circle şeklinde pager oluşturma
@Composable
fun Indicator(isSelected: Boolean) {
    //pager arasında geçiş yaparkenki animasyonu sağlayan bölüm
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )
    //Pager alanı için yükseklik, şekil vb görsel özelliklerini tanımlama
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width = width.value)
            .clip(shape = CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground.copy(
                    alpha = 0.5f
                )
            )
    ) {

    }

}
//OnBoardingData sınıfından gelen resim ve yazıların arayüz elementlerine aktarılması
@Composable
fun OnBoardingItem(item: OnBoardingData) {
    //İçereklerin konumu belirleniyorz
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        //Resim, image özelliğine atanıyor
        Image(painter = painterResource(id = item.imageR), contentDescription = null)
        //Bold ana başlık Text özelliğine atanıyor
        Text(
            text = stringResource(id = item.titleR),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
        //Açıklama yazısı Text özelliğine atanıyor
        Text(
            text = stringResource(id = item.textR),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}