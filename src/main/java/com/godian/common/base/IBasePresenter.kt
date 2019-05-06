package com.godian.common.base

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface IBasePresenter : LifecycleObserver, View.OnClickListener{

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory()

    fun onWidgetClick(view: View)
}