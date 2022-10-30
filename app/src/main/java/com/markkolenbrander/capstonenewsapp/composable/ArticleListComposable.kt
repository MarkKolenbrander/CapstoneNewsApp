package com.markkolenbrander.capstonenewsapp.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.markkolenbrander.capstonenewsapp.R
import com.markkolenbrander.capstonenewsapp.composetheme.AppTheme
import com.markkolenbrander.capstonenewsapp.models.Article
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay


@Composable
fun ArticleNewsItem(article: List<Article>, onItemClicked: (Article) -> Unit) {

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(1000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
    ) {

        LazyColumn(modifier = Modifier.padding(top = 4.dp)){
            items(article){ article ->
                Surface(
                    modifier = Modifier.clickable { onItemClicked(article) },
                    color = AppTheme.colors.background) {
                    Column(modifier = Modifier
                        .padding(top = 8.dp)
                        .fillParentMaxWidth() ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(8.dp)) {
                            if (article.urlToImage != null) {
                                GlideImage(
                                    imageModel = { article.urlToImage},
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp),
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center,
                                        )
                                )
                            }else {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_no_pictures),
                                    contentDescription = "No Image",
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp)
                                )
                            }
                            Text(
                                text = article.title,
                                color = AppTheme.colors.textPrimary,
                                modifier = Modifier.padding(start = 18.dp),
                            )
                        }
                        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text = article.source.name,
                                color = AppTheme.colors.textSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 16.dp),
                                textAlign = TextAlign.Start,
                            )

                            article.publishedAt?.let { Text(
                                text = it,
                                color = AppTheme.colors.textSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            ) }

                        }
                        Divider(thickness = 1.dp, color = AppTheme.colors.secondary, modifier = Modifier.padding(top = 8.dp) )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewList() {
//    ArticleNewsItem(
//        listOf(
//            Article(
//                Source(
//                "ID 1",
//                "Name",
//                "Description",
//                "www.url.com",
//                Category.GENERAL,
//                Language.NL,
//                Country.NL
//            ),
//                "Author",
//                "The Title",
//                "Description",
//                "www.urlTest2.com", "www.imgURL.com", "2022-10-29", "The Content",
//            ),
//            Article(
//                Source(
//                    "ID 1",
//                    "Name",
//                    "Description",
//                    "www.url.com",
//                    Category.GENERAL,
//                    Language.NL,
//                    Country.NL
//                ),
//                "Author",
//                "The Title",
//                "Description",
//                "www.urlTest2.com", "www.imgURL.com", "2022-10-29", "The Content",
//            ),
//        )
//    )
//}