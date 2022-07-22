package com.enesfaruk.cryptoapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.enesfaruk.cryptoapp.utils.loadImage

/**
 * Created by Enes Faruk Işık on 21.07.2022.
 */
class CoinBinding {

    companion object {

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, coinImage: String) {
            val imageUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/$coinImage.png"
            imageView.loadImage(imageUrl)
        }
    }
}