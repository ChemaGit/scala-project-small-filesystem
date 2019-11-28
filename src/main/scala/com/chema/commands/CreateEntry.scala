package com.chema.commands

import com.chema.files.{DirEntry, Directory}
import com.chema.filesystem.State

abstract class CreateEntry(entryName: String) extends Command {

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hasEntry(entryName)) {
      state.setMessage(s"Entry $entryName already exists!")
    } else if(entryName.contains(Directory.SEPARATOR)) {
      // mkdir something/somethingElse if forbidden for now
      state.setMessage(s"$entryName must not contain separators!")
    }else if(checkIllegal(entryName)) {
      state.setMessage(s"$entryName: illegal entry name!")
    }else {
      doCreateEntry(state, entryName)
    }
  }

  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
      /*
      /a/b
        (contents)
        (new entry) /e

        updateStructure(root, ["a", "b"], /e)
        => path.isEmpty?
        => oldEntry = /a
        root.replaceEntry("a", updateStructure(/a, ["b"], /e))
        => path.isEmpty?
        => oldEntry = /b
        /a.replaceEntry("b", updateStructure(/b, [], /e))
        => path.isEmpty? => /b.add(/e)
       */
    }

    val wd = state.wd

    // 1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    // val newDir = Directory.empty(wd.path, name)
    // TODO: implement this
    val newEntry: DirEntry = createSpecificEntry(state)

    // 3. update the whole directory structure starting from the root
    // (the directory structure is INMUTABLE)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. find new working directory INSTANCE given wd's path, int the NEW directory structure
    val newWd = newRoot.findDescendent(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry
}
