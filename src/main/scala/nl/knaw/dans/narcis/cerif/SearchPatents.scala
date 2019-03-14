package nl.knaw.dans.narcis.cerif

import better.files.File

import scala.xml.{ Utility, XML }

/**
 * Search for `knaw_long` files that represent a 'patent' and copy them to `target/patents/[repository_name]`
 */
object SearchPatents extends App {

  // fill in the repository you want to search
  val repositoryName = "tue"

  val outputDir = File("target/patents") / repositoryName
  if (outputDir.notExists)
    outputDir.createDirectories()

  val store = File("/Volumes/narcis-store")
  val repository = store / repositoryName
  val patentLongFiles = for {
    layer1 <- repository.children
    layer2 <- layer1.children
    publications <- layer2.children
    knawLong = publications / "knaw_long"
    if knawLong.exists
    if isPatent(knawLong)
    _ = println(knawLong)
    _ = knawLong copyTo (outputDir / s"${ publications.name }.xml")
  } yield knawLong

  patentLongFiles.foreach(println)

  def isPatent(file: File): Boolean = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "metadata" \ "genre").text == "patent"
  }
}
