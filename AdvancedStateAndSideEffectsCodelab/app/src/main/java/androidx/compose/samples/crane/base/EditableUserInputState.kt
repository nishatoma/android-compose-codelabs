package androidx.compose.samples.crane.base

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

class EditableUserInputState(private val hint: String, initialText: String) {
    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint

    // Need to implement Saver in order to tell our app
    // how to save and restore our use input text
    companion object {
        val Saver: Saver<EditableUserInputState, *> = listSaver(
            save = { listOf(it.hint, it.text)},
            restore = {
                EditableUserInputState(hint = it[0],
                initialText = it[1])
            }
        )
    }

}

// Create a method to remember the state input state
@Composable
fun rememberEditableUserInputState(hint: String): EditableUserInputState =
    rememberSaveable(hint, saver = EditableUserInputState.Saver) {
        EditableUserInputState(hint, hint)
    }