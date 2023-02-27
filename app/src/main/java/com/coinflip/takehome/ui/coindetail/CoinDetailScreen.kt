package com.coinflip.takehome.ui.coindetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.coinflip.takehome.R
import com.coinflip.takehome.data.local.database.CoinEntity

private val emptyCoin = CoinEntity(
    "",
    "",
    0.0,
    "Empty",
    "",
    0.0,
    0.0,
    0.0
)

@Composable
fun CoinDetailScreen(
    modifier: Modifier,
    viewModel: CoinDetailViewModel = hiltViewModel()){

    val coin = viewModel.coin.collectAsState(
        initial = emptyCoin
    )
    CoinDetail(modifier = modifier, coinEntity = coin)
}

@Composable
fun CoinDetail(
    modifier: Modifier = Modifier,
    coinEntity: State<CoinEntity>
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = coinEntity.value.image,
                placeholder = painterResource(id = R.drawable.dummy),
                contentDescription = stringResource(id = R.string.coin_image),
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = coinEntity.value.name,
                    style = MaterialTheme.typography.headlineLarge
                )

                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.price),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = coinEntity.value.price.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.market_cap),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = coinEntity.value.market_cap.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.total_volume),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = coinEntity.value.total_volume.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }

}