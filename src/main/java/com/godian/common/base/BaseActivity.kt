package com.godian.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.godian.common.util.AntiShakeUtils
import com.godian.common.util.AppUtils
import com.godian.common.util.ToastUtils
import com.godian.common.util.Utils
import com.r0adkll.slidr.Slidr
import java.util.*

/**
 * ```
 * author: blankj
 * blog  : http://blankj.com
 * time  : 2018/11/16
 * desc  : base about activity
 * ```
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    protected lateinit var mContentView: View
    protected lateinit var mActivity: Activity
    private lateinit var mPresenter: IBasePresenter

    abstract fun isSwipeBack(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = this
        super.onCreate(savedInstanceState)
        initData(intent.extras)
        setRootLayout(bindLayout())
        initView(savedInstanceState, mContentView)
        doBusiness()
        if (isSwipeBack()) {
            Slidr.attach(this)
        }
        AppUtils.registerAppStatusChangedListener(this, object : Utils.OnAppStatusChangedListener {
            override fun onForeground() {
                ToastUtils.showShort("foreground")
            }

            override fun onBackground() {
                ToastUtils.showShort("background")
            }
        })
    }

    fun bindPresenter(presenter: IBasePresenter){
        mPresenter = presenter
    }

    fun <T : IBasePresenter> getPresenter() : T? {
        @Suppress("UNCHECKED_CAST")
        return mPresenter as T
    }

    override fun setRootLayout(layoutId: Int) {
        if (layoutId <= 0) return
        mContentView = LayoutInflater.from(this).inflate(layoutId, null)
        setContentView(mContentView)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppUtils.unregisterAppStatusChangedListener(this)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        AppUtils.changeSystemLanguage(newBase, Locale.CHINESE)
    }
}