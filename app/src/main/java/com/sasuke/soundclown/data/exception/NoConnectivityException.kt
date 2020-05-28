package com.sasuke.soundclown.data.exception

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String?
        get() = "Internet not connected"
}