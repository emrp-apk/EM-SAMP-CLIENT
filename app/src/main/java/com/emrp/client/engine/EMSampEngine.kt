package com.emrp.client.engine

import java.net.InetSocketAddress
import java.net.Socket

class EMSampEngine {

    companion object {
        const val SERVER_HOST = "play.emrp.online"
        const val SERVER_PORT = 2026
    }

    enum class State {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        ERROR
    }

    var state: State = State.DISCONNECTED
        private set

    fun connect(onResult: (State) -> Unit) {
        state = State.CONNECTING
        onResult(state)

        Thread {
            try {
                Socket().use { socket ->
                    socket.connect(
                        InetSocketAddress(SERVER_HOST, SERVER_PORT),
                        5000
                    )
                }

                state = State.CONNECTED
                onResult(state)

            } catch (_: Exception) {
                state = State.ERROR
                onResult(state)
            }
        }.start()
    }

    fun disconnect() {
        state = State.DISCONNECTED
    }
}
