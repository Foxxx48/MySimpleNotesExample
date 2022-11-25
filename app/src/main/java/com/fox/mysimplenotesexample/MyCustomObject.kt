package com.fox.mysimplenotesexample

class MyCustomObject() {

    interface MyCustomObjectListener {
        fun onObjectReady()
    }

    fun setOnMyCustomObjectListener(listener: MyCustomObjectListener?) {
        var myCustomObjectListener: MyCustomObjectListener? = listener
    }
}