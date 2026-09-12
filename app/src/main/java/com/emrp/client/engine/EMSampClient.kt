package com.emrp.client.engine

class EMSampClient {

    enum class State {
        STOPPED,
        STARTING,
        RUNNING,
        ERROR
    }

    var state = State.STOPPED
        private set

    fun start() {
        state = State.STARTING

        // EM-SAMP engine initialization will be built here.
        // Rendering, networking and game systems will be added
        // as separate components.

        state = State.RUNNING
    }

    fun stop() {
        state = State.STOPPED
    }
}
