package pl.madshai.runboard.configuration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Created by daniel.madejek on 2017-06-02.
 */

fun loadShortcuts() : List<Shortcut>{
    return jacksonObjectMapper().readValue<List<Shortcut>>(File("test.json"))
}

data class Shortcut (val name : String, val path: String)
