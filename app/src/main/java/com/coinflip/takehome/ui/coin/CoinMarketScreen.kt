package com.coinflip.takehome.ui.coin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.coinflip.takehome.R

@Composable
fun CoinMarketScreen(
    modifier: Modifier,
    onCoinClick: (String) -> Unit,
    viewModel: CoinMarketViewModel = hiltViewModel()) {


    val coins = viewModel.getCoins().collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyColumn(modifier = modifier){

        items(coins, key = { coin -> coin.id }){
            coin ->
                CoinCard(
                    coinName = coin?.name ?: "",
                    price = coin?.price.toString(),
                    imgUrl = coin?.image ?: "",
                onClick = {
                    onCoinClick(coin?.id ?: "")
                    showToastMessage(context, coin?.id ?: "MH")
                })
            Divider()
        }

        if (coins.loadState.refresh == LoadState.Loading ||
            coins.loadState.append == LoadState.Loading ||
            coins.loadState.prepend == LoadState.Loading){
            item {
                LoadingItem()
            }
        }
    }

}

@Preview
@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier)
    }
}

fun showToastMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinCard(modifier: Modifier = Modifier,
             coinName: String,
             price: String,
             imgUrl: String,
             onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imgUrl,
                placeholder = painterResource(id = R.drawable.dummy),
                contentDescription = stringResource(id = R.string.coin_image),
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = coinName,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun CoinCardsList(
    modifier: Modifier = Modifier,
    onCoinSelection: (String) -> Unit
){

    LazyColumn(modifier = modifier){
        items(items = favoriteCollectionsData, key = { item ->  item.first }){
            item ->  CoinCard(coinName = item.first, price = item.second, imgUrl = "") {
                // Click listener
        }
            Divider()
        }
    }
}

@Preview(showBackground = true,  backgroundColor = 0xFFCCC2DC)
@Composable
fun PreviewCoinCardsList(){
    CoinCardsList(){
        // item selection listener
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCCC2DC)
@Composable
fun PreviewCoinCard() {
    CoinCard(coinName = "Bitcoin", price = "500", imgUrl = "", onClick = {})
}

private val favoriteCollectionsData = listOf(
    "Bitcoin" to "500",
    "Ethereum" to "400",
    "Ripple" to "0.65",
    "Binance" to "15",
    "Shib" to "0.5"
)