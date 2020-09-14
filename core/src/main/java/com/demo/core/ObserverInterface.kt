package com.demo.core

interface ObserverInterface<T> {
    fun onObserve(requestCode: Int, requestMessage: String?, eventData: T?)
}