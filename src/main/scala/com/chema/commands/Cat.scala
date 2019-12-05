package com.chema.commands
import com.chema.filesystem.State

class Cat(filename: String) extends Command {
  override def apply(state: State): State = {
    // display the content only in the current directory
    val wd = state.wd
    val dirEntry = wd.findEntry(filename)
    if (dirEntry == null || !dirEntry.isFile) state.setMessage(s"$filename: no such a file")
    else state.setMessage(dirEntry.asFile.contents)
  }
}
