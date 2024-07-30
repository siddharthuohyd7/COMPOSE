package com.example.composemvvm.ui.theme

import android.os.Bundle
import androidx.navigation.NavType
import com.example.composemvvm.ui.theme.models.MovieItem
import com.google.gson.Gson

class AssetParamType : NavType<MovieItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): MovieItem {
        return Gson().fromJson(value, MovieItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: MovieItem) {
        bundle.putParcelable(key, value)
    }
}