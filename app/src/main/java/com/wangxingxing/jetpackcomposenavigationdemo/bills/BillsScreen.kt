package com.wangxingxing.jetpackcomposenavigationdemo.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.wangxingxing.jetpackcomposenavigationdemo.R
import com.wangxingxing.jetpackcomposenavigationdemo.components.BillRow
import com.wangxingxing.jetpackcomposenavigationdemo.components.StatementBody
import com.wangxingxing.jetpackcomposenavigationdemo.data.Bill

/**
 * author : 王星星
 * date : 2021/9/23 15:35
 * email : 1099420259@qq.com
 * description : The Bills screen.
 */

@Composable
fun BillsBody(bills: List<Bill>) {
    StatementBody(
        items = bills,
        colors = { bill -> bill.color },
        amounts = { bill -> bill.amount },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due)
    ) { bill ->
        BillRow(name = bill.name, due = bill.due, amount = bill.amount, color = bill.color)
    }
}