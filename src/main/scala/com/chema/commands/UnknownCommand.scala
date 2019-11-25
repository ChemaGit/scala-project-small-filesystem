package com.chema.commands
import com.chema.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State = state.setMessage("Command not found!")
}
