/*
 * #%L
 * RunBoard
 * %%
 * Copyright (C) 2017 Daniel Madejek
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package pl.madshai.runboard.configuration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Load shortcuts from configuration file shortcuts.json
 */
fun loadShortcuts() : List<Shortcut>{
    return jacksonObjectMapper().readValue<List<Shortcut>>(File("shortcuts.json"))
}

/**
 * Save shortcuts to configuration file shortcuts.json
 */
fun saveShortcuts(shortcuts : List<Shortcut>){
    jacksonObjectMapper().writeValue(File("shortcuts.json"),shortcuts)
}

/**
 * Class describing shortcut
 */
data class Shortcut (var name : String, var command: String)
