package com.emrp.client.engine

class EMGameState {

    val input = EMInput()
    val player = EMPlayer(y = 1f)
    val controller = EMPlayerController(player, input)

    fun update() {
        controller.update()
    }
}
