package org.iikun.iik

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform