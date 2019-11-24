package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.statemachine.Action

class Onboarding : State {
    override fun consumeAction(action: Action): State {
        return when (action) {
            is Action.ScreenNavigation -> MovieList()
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}