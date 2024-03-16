package com.example.dailyshoes.ui.firstDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular


class AccountAndSettings {

    var switchState by mutableStateOf(false)


    @Composable
    fun AccountSetting() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {

            TitleBar("Account & Settings")

            Text(
                text = "Account",
                style = TextStyle(
                    fontFamily = Poppins_MEDIUM, fontSize = 16.sp,
                ),
                modifier = Modifier.padding(20.dp)
            )

            val list = listOf(
                R.drawable.ic_side_notifications to "Notification Setting",
                R.drawable.ic_shipping_address to "Shipping Address",
                R.drawable.ic_payment_info to "Payment Info",
                R.drawable.ic_delete_as to "Delete Account",
            )
            list.forEachIndexed { index, pair ->
                AccountItem(image = pair.first, title = pair.second) {}
            }

            Text(
                text = "App Settings",
                style = TextStyle(
                    fontFamily = Poppins_MEDIUM, fontSize = 16.sp,
                ),
                modifier = Modifier.padding(20.dp)
            )

            val settingList = listOf(
                "Enable Face ID For Log In", "Enable Push Notifications",
                "Enable Location Services", "Dark Mode"
            )

            settingList.forEachIndexed { index, s ->
                SettingItem(s, true)
            }
        }
    }


    @Composable
    fun AccountItem(
        image: Int,
        title: String,
        onItemClicked: () -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable { onItemClicked.invoke() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = image), contentDescription = "")
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = Poppins_Regular, fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = ""
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(11.dp)
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .background(color = Color.LightGray)
        )
    }


    @Composable
    fun SettingItem(
        setting: String,
        isCheck: Boolean,
        onSwitchChanged: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = setting,
                style = TextStyle(
                    fontFamily = Poppins_Regular, fontSize = 14.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Switch(
                colors = SwitchDefaults.colors(
                    uncheckedBorderColor = Color.LightGray,
                    checkedTrackColor = colorResource(id = R.color.intro_get_started),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                ),
                checked = isCheck,
                onCheckedChange = { onSwitchChanged.invoke() },
            )

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(11.dp)
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .background(color = Color.LightGray)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AccountSetting()
    }

}