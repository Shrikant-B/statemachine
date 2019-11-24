package com.shrikantb.statemachine.domain.statemachine.state

import com.shrikantb.statemachine.domain.statemachine.Action

class TerminateCurrent :
    State {
    override fun consumeAction(action: Action) = this
}