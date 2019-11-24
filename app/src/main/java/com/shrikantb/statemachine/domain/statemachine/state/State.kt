package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.statemachine.Action

interface State {
    fun consumeAction(action: Action) : State
}