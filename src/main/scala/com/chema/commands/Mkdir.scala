package com.chema.commands
import com.chema.files.Directory
import com.chema.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {
    ???
  }

  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hasEntry(name)) {
      state.setMessage(s"Entry $name already exists!")
    } else if(name.contains(Directory.SEPARATOR)) {
      // mkdir something/somethingElse if forbidden for now
      state.setMessage(s"$name must not contain separators!")
    }else if(checkIllegal(name)) {
      state.setMessage(s"$name: illegal entry name!")
    }else {
      doMkdir(state, name)
    }
  }

}
