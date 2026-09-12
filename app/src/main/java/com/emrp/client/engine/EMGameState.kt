package com.emrp.client.engine

class EMGameState {

    val input = EMInput()
    val player = EMPlayer()
    val controller = EMPlayerController(player, input)

    fun update() {
        controller.update()
    }
}
