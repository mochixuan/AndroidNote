package com.wx.review.impl

import android.support.annotation.IntDef
import com.wx.review.data.constant.DataConstant

@IntDef(
    DataConstant.one,
    DataConstant.two,
    DataConstant.three,
    DataConstant.four,
    DataConstant.five,
    DataConstant.six,
    DataConstant.seven
)
@Retention(AnnotationRetention.SOURCE)
annotation class WeekDay

class TestKotlinAnnotation {

    companion object {

        @WeekDay
        var mCurWorkDay: Int = DataConstant.eight

        fun test() {

        }


        fun setWorkDay(day: Int) {

        }

    }

}