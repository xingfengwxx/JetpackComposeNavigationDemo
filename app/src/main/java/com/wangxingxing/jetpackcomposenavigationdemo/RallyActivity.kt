package com.wangxingxing.jetpackcomposenavigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navDeepLink
import com.wangxingxing.jetpackcomposenavigationdemo.accounts.AccountsBody
import com.wangxingxing.jetpackcomposenavigationdemo.accounts.SingleAccountBody
import com.wangxingxing.jetpackcomposenavigationdemo.bills.BillsBody
import com.wangxingxing.jetpackcomposenavigationdemo.components.RallyTabRow
import com.wangxingxing.jetpackcomposenavigationdemo.data.UserData
import com.wangxingxing.jetpackcomposenavigationdemo.overview.OverviewBody
import com.wangxingxing.jetpackcomposenavigationdemo.ui.theme.RallyTheme

class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = RallyScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )
        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen -> 
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = RallyScreen.Overview.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                // 构建导航图，我们有哪些destination可以去
                composable(RallyScreen.Overview.name) {
                    OverviewBody(
                        onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                        onClickSeeAllBills =  { navController.navigate(RallyScreen.Bills.name) },
                        onAccountClick = { name ->
                            navigateToSingleAccount(navController, name)
                        }
                    )
                }
                composable(RallyScreen.Accounts.name) {
                    AccountsBody(UserData.accounts)
                }
                composable(RallyScreen.Bills.name) {
                    BillsBody(UserData.bills)
                }
                composable(
                    route = "${RallyScreen.Accounts.name}/{name}",
                    arguments = listOf(
                        // 参数类型
                        navArgument("name") {
                            type = NavType.StringType
                        }
                    ),
                    deepLinks = listOf(
                        navDeepLink { 
                            uriPattern = "rally://${RallyScreen.Accounts.name}/{name}"
                        }
                    )
                ) { entry ->
                    // 获取传递过来的参数值，并展示内容
                    val accountName = entry.arguments?.getString("name")
                    val account = UserData.getAccount(accountName)
                    SingleAccountBody(account = account)
                }
            }
        }
    }
}

private fun navigateToSingleAccount(
    navController: NavHostController,
    accountName: String
) {
    navController.navigate("${RallyScreen.Accounts.name}/$accountName")
}