package com.chema.commands
import com.chema.filesystem.State

class Exit extends Command {
  override def apply(state: State): State = state
  System.exit(1)
}
