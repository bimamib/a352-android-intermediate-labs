package com.dicoding.picodiploma.productdetail

import java.text.NumberFormat

class Helper {

    fun String.withNumberingFormat(): String {
        return NumberFormat.getNumberInstance().format(this.toDouble())
    }
}