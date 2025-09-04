package com.example.acuario_poo

open class SmartDevice(
    val name: String,
    val category: String,
    open val deviceType: String
) {
    var deviceStatus: String = "off"
        private set
    var deviceTurnOnCount: Int = 0
        private set

    fun turnOn() {
        if (deviceStatus != "on") {
            deviceStatus = "on"
            deviceTurnOnCount += 1
        }
    }

    fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(
    name: String,
    category: String
) : SmartDevice(name, category, deviceType = "Smart TV") {
    var speakerVolume: Int = 10
        private set
    var channelNumber: Int = 5
        private set

    fun decreaseVolume() {
        if (deviceStatus == "on") {
            speakerVolume = (speakerVolume - 1).coerceAtLeast(0)
            println("TV volume: $speakerVolume")
        } else {
            println("TV apagada: no es posible cambiar el volumen")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on") {
            channelNumber = (channelNumber - 1).coerceAtLeast(1)
            println("TV channel: $channelNumber")
        } else {
            println("TV apagada: no es posible cambiar de canal")
        }
    }
}

class SmartLightDevice(
    name: String,
    category: String
) : SmartDevice(name, category, deviceType = "Smart Light") {
    var brightness: Int = 50
        private set

    fun decreaseBrightness() {
        if (deviceStatus == "on") {
            brightness = (brightness - 5).coerceAtLeast(0)
            println("Light brightness: $brightness")
        } else {
            println("Luz apagada: no es posible cambiar el brillo")
        }
    }
}

class SmartHome(
    val tv: SmartTvDevice,
    val light: SmartLightDevice
) {
    fun turnOnTv() = tv.turnOn()
    fun turnOffTv() = tv.turnOff()
    fun turnOnLight() = light.turnOn()
    fun turnOffLight() = light.turnOff()

    fun decreaseTvVolume() {
        if (tv.deviceStatus == "on") {
            tv.decreaseVolume()
        } else {
            println("Acción no permitida: TV está apagada")
        }
    }

    fun changeTvChannelToPrevious() {
        if (tv.deviceStatus == "on") {
            tv.previousChannel()
        } else {
            println("Acción no permitida: TV está apagada")
        }
    }

    fun printSmartTvInfo() {
        if (tv.deviceStatus == "on") {
            tv.printDeviceInfo()
            println("TV turn-on count: ${tv.deviceTurnOnCount}")
        } else {
            println("Acción no permitida: TV está apagada")
        }
    }

    fun printSmartLightInfo() {
        if (light.deviceStatus == "on") {
            light.printDeviceInfo()
            println("Light turn-on count: ${light.deviceTurnOnCount}")
        } else {
            println("Acción no permitida: Luz está apagada")
        }
    }

    fun decreaseLightBrightness() {
        if (light.deviceStatus == "on") {
            light.decreaseBrightness()
        } else {
            println("Acción no permitida: Luz está apagada")
        }
    }
}

fun main() {
    val tv = SmartTvDevice(name = "Sala TV", category = "Entretenimiento")
    val light = SmartLightDevice(name = "Lampara Sala", category = "Iluminación")
    val home = SmartHome(tv, light)

    home.turnOnTv()
    home.turnOnLight()

    home.printSmartTvInfo()
    home.printSmartLightInfo()

    home.decreaseTvVolume()
    home.changeTvChannelToPrevious()
    home.decreaseLightBrightness()

    home.turnOnTv()
    home.printSmartTvInfo()
}