package com.emrp.client.engine

class EMInput {

    var forward = false
    var backward = false
    var left = false
    var right = false
    var jump = false
    var action = false

    fun reset() {
        forward = false
        backward = false
        left = false
        right = false
        jump = false
        action = false
    }
}
