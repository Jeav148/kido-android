package com.jarval.core.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor(){

    private val _navEvents = MutableSharedFlow<Screen>(extraBufferCapacity = 1)
    val navEvents = _navEvents.asSharedFlow()

    fun navigateTo(screen: Screen){
        _navEvents.tryEmit(screen)
    }
}