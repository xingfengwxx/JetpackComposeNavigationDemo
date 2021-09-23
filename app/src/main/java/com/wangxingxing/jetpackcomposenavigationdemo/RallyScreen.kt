package com.wangxingxing.jetpackcomposenavigationdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.wangxingxing.jetpackcomposenavigationdemo.accounts.AccountsBody
import com.wangxingxing.jetpackcomposenavigationdemo.bills.BillsBody
import com.wangxingxing.jetpackcomposenavigationdemo.overview.OverviewBody
import com.wangxingxing.jetpackcomposenavigationdemo.data.UserData

/**
 * author : 王星星
 * date : 2021/9/23 14:26
 * email : 1099420259@qq.com
 * description :
 */

enum class RallyScreen(
    val icon: ImageVector,
    val body: @Composable ((String) -> Unit) -> Unit
) {
    Overview(
        icon = Icons.Filled.PieChart,
        body = { OverviewBody() }
    ),
    Accounts(
        icon = Icons.Filled.AttachMoney,
        body = { AccountsBody(UserData.accounts)}
    ),
    Bills(
        icon = Icons.Filled.MoneyOff,
        body = { BillsBody(UserData.bills) }
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): RallyScreen =
            when (route?.substringBefore("/")) {
                Accounts.name -> Accounts
                Bills.name -> Bills
                Overview.name -> Overview
                null -> Overview
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}