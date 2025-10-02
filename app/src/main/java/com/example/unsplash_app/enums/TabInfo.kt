package com.example.unsplash_app.enums

import android.content.Context
import androidx.annotation.StringRes
import com.example.unsplash_app.R

enum class TabInfo(val position: Int, @StringRes val tabNameId: Int) {

    TabHome(0, R.string.tab_home),
    TabCollection(1, R.string.tab_collection);

    companion object{
        fun getTabCount() = entries.size
        fun getTabByPosition(position: Int) = entries.getOrNull(position) ?: TabHome
        fun getTabName(context: Context, position: Int): String {
            return context.getString(getTabByPosition(position).tabNameId)
        }
    }

}