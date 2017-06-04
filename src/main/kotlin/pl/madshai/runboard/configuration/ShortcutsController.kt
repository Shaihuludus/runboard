package pl.madshai.runboard.configuration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Created by daniel.madejek on 2017-06-02.
 */

fun loadShortcuts() : List<Shortcut>{
    return jacksonObjectMapper().readValue<List<Shortcut>>(File("shortcuts.json"))
}

fun saveShortcuts(shortcuts : List<Shortcut>){
    jacksonObjectMapper().writeValue(File("shortcuts.json"),shortcuts)
}

data class Shortcut (var name : String, var command: String)
