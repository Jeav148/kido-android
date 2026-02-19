package com.jarval.kido.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent, State, Effect>(
    initialState: State
) : ViewModel() {

    // --- STATE ---
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    // --- EFFECT ---
    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    // --- INTENT HANDLING ---
    fun processIntent(intent: Intent) {
        handleIntent(intent)
    }

    protected abstract fun handleIntent(intent: Intent)

    // --- REDUCER ---
    protected fun reduce(reducer: State.() -> State) {
        _state.update { current -> current.reducer() }
    }

    // --- EFFECT EMITTER ---
    protected fun emitEffect(builder: () -> Effect){
        viewModelScope.launch {
            _effect.emit(builder())
        }
    }
}