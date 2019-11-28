package com.chema.commands
import com.chema.files.DirEntry
import com.chema.filesystem.State

class Ls extends Command {

  def createNiceOutput(contents: List[DirEntry]): String = {
    if(contents.isEmpty) ""
    else {
      val entry = contents.head
      s"${entry.name}[${entry.getType}]\n${createNiceOutput(contents.tail)}"
    }
  }

  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput)
  }
}
