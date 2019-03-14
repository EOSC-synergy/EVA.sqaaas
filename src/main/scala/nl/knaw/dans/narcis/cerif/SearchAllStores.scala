package nl.knaw.dans.narcis.cerif

import better.files.File

import scala.xml.{ Utility, XML }

/**
 * Iterate through all stores and search for the 'rightsDescription' in all `knaw_long` files.
 */
object SearchAllStores extends App {
  val store = File("/Volumes/narcis-store")
  val allLongFiles = for {
    substore <- store.children
    layer1 <- substore.children
    layer2 <- layer1.children
    publications <- layer2.children
    knawLong = publications / "knaw_long"
    if knawLong.exists
  } yield knawLong

  allLongFiles.flatMap(findRightsDescription).foreach(println)

  def findRightsDescription(file: File): Seq[String] = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "metadata" \ "rightsDescription").map(_.text)
  }
}
