package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.statemachine.Action

class BeforeApiCall : State {
    override fun consumeAction(action: Action) = this
}