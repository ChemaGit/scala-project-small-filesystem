package com.chema.commands
import com.chema.files.{DirEntry, Directory}
import com.chema.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry = {
    Directory.empty(state.wd.path, name)
  }

}
