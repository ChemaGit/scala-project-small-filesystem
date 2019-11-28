package com.chema.commands
import com.chema.files.{DirEntry, File}
import com.chema.filesystem.State

class Touch(name: String) extends CreateEntry(name){

  override def createSpecificEntry(state: State): DirEntry = {
    File.empty(state.wd.path, name)
  }

}
