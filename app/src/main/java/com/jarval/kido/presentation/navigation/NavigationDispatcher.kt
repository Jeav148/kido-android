package com.jarval.kido.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor(){

    private val _navigationEvents = MutableSharedFlow<Screen>(extraBufferCapacity = 1)
    val navigationEvents = _navigationEvents.asSharedFlow()

    fun navigateTo(screen: Screen){
        _navigationEvents.tryEmit(screen)
    }

}