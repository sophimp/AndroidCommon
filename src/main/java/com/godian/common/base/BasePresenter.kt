package com.godian.common.base

import android.view.View
import com.godian.common.util.AntiShakeUtils

/**
 * mvvm 中间交互层
 */
abstract class BasePresenter : IBasePresenter{

    override fun onClick(view: View) {
        if (AntiShakeUtils.isValid(view)) {
            onWidgetClick(view)
        }
    }
}