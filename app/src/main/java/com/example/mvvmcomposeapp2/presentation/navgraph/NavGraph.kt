package com.loc.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mvvmcomposeapp2.domain.model.Article
import com.example.mvvmcomposeapp2.domain.model.Source
import com.example.mvvmcomposeapp2.presentation.bookmark.BookmarkScreen
import com.example.mvvmcomposeapp2.presentation.bookmark.BookmarkViewModel
import com.example.mvvmcomposeapp2.presentation.details.DetailsScreen
import com.example.mvvmcomposeapp2.presentation.navgraph.Route
import com.example.mvvmcomposeapp2.presentation.onboarding.OnBoardingScreen
import com.example.mvvmcomposeapp2.presentation.onboarding.OnBoardingViewModel
import com.example.mvvmcomposeapp2.presentation.search.SearchScreen
import com.example.mvvmcomposeapp2.presentation.search.SearchViewModel
import com.example.mvvmcomposeapp2.ui.theme.MVVMComposeApp2Theme

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
               /* val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = {})*/

                /*val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})*/

                /*MVVMComposeApp2Theme(dynamicColor = false) {
                    DetailsScreen(
                        article = Article(
                            author = "",
                            title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                            description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                            content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                            publishedAt = "2023-06-16T22:24:33Z",
                            source = Source(
                                id = "", name = "bbc"
                            ),
                            url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
                            urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
                        ),
                        event = {}
                    ) {

                    }
                }*/

                val viewModel: BookmarkViewModel = hiltViewModel()
              BookmarkScreen(state = viewModel.state.value, navigate = {})
            }
        }
    }
}