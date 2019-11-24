package com.shrikantb.statemachine.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shrikantb.statemachine.domain.statemachine.Action
import com.shrikantb.statemachine.domain.statemachine.state.State
import com.shrikantb.statemachine.domain.statemachine.state.Onboarding

class SplashViewModel : ViewModel() {
    private var state: State = Onboarding()
    val stateObserver: MutableLiveData<State> = MutableLiveData()

    fun changeState(action: Action) {
        stateObserver.value = state.consumeAction(action)
    }
}