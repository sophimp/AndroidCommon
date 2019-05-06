package com.godian.common.base

import android.os.Bundle
import android.view.View

/**
 * ```
 * author: blankj
 * blog  : http://blankj.com
 * time  : 2018/11/16
 * desc  :
 * ```
 */
interface IBaseView  {

    fun initData(bundle: Bundle?)
    fun bindLayout(): Int
    fun setRootLayout(layoutId: Int)
    fun initView(savedInstanceState: Bundle?, contentView: View)
    fun doBusiness()

}
