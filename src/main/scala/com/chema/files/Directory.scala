package com.chema.files

class Directory (override val parentPath: String, override val name: String, val contents: List[DirEntry])extends DirEntry(parentPath, name) {

}

object Directory {
  val SEPARATOR = "/"
  val ROOT = "/"

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())


}
