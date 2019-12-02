package com.chema.files

import com.chema.filesystem.FilesystemException

class Directory (override val parentPath: String, override val name: String, val contents: List[DirEntry])extends DirEntry(parentPath, name) {
  def hasEntry(name: String): Boolean = findEntry(name) != null

  def getAllFoldersInPath: List[String] = {
    // /a/b/c/d => List["a","b","c","d"]
    path.substring(1).split(Directory.SEPARATOR).toList.filter(x => !x.isEmpty)
  }

  def findDescendent(path: List[String]): Directory = {
    if(path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendent(path.tail)
  }

  def addEntry(newEntry: DirEntry): Directory = {
    new Directory(parentPath, name, contents :+ newEntry)
  }

  def findEntry(entryName: String): DirEntry = {
    @annotation.tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if(contentList.isEmpty) null
      else if(contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)
    }
    findEntryHelper(entryName,contents)
  }

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory = {
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)
  }

  def isRoot: Boolean = parentPath.isEmpty

  def asDirectory: Directory = this

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false

  override def asFile: File = throw new FilesystemException("A directory cannot be converted to a file!")

  override def getType: String = "Directory"
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("","")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())

}
