package com.godian.common.base

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.*
import com.godian.common.R
import kotlinx.android.synthetic.main.dialog_common.*

/**
 * Dialog 封装， 使用 DialogFragment 替代 Dialog实现
 *
 * 对Dialog有些陌生， 以前的使用也只限于网上找一些代码， 封装一下标题和按钮，对于原始的Dialog创建还真不清楚。
 * 可见这几年开发， 真得只是浮于表面，从第一年的心总想往游戏上转开始， 学的东西一味追求多， 没有追求深
 *
 * Dialog 与window是什么关系， 与Activity 有何关系，
 * DialogFragment 的dismiss 与 show 每次都得重走生命周期， 这样的话， Dialog岂不是一次性的？
 *
 * 如何决定一个对象的生命周期， 还是跟使用场景有关， 如果Dialog弹出的很频繁， 是可以将Dialog的生命周期延长，
 *
 * 为何要延长生命周期呢？ 重新创建一个对象的开销在哪里，内存分配与GC回收。
 *
 * 为什么要封装DialogFragment？
 *  1. 为了统一标题与按钮的风格, 使用原生的title, nagative, positive 不行吗?
 *  2. 可以像使用Dialog一样简单使用, 但是保存 DialogFragment优点
 *  3. 方便扩展, 容易新增其他类型的Dialog
 *
 * 分析源码， 得从Fragment着手,
 *
 */
abstract class BaseDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (dialogStyle() == 0) {
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.default_dialog_style)
        } else {
            setStyle(DialogFragment.STYLE_NO_FRAME, dialogStyle())
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (animationStyle() != 0) {
            dialog.window.setWindowAnimations(animationStyle())
        }
        var inflater = LayoutInflater.from(activity)
        var rootView = inflater.inflate(R.layout.dialog_common, container, false)
        fl_dialog_content.addView(inflater.inflate(layoutId(), fl_dialog_content, false))
        initView(rootView)
        return rootView
    }

    abstract fun initView(rootView: View?)

    public fun setGravity(gravity: Int){
        if(dialog != null){
            dialog.window.attributes.gravity = gravity
        }
    }
    public fun isShowing(): Boolean {
        return dialog != null && dialog.isShowing
    }

    abstract fun layoutId(): Int

    protected fun dialogStyle(): Int {
        return 0
    }

    protected fun animationStyle(): Int {
        return 0
    }
}