package com.chema.commands
import com.chema.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State = state.setMessage(state.wd.path)

}
