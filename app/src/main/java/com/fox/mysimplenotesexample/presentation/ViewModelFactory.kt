package com.fox.mysimplenotesexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.Provider


class ViewModelFactory (
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, ViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass] as T

    }
}