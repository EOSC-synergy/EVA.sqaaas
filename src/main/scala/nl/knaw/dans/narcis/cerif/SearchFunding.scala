package nl.knaw.dans.narcis.cerif

import better.files.File

import scala.xml.{ Utility, XML }

/**
 * Search for `knaw_long` files that represent a 'funding' and copy them to `target/fundings/[repository_name]`
 */
object SearchFunding extends App {

  // fill in the repository you want to search
  val repositoryName = "um"

  val outputDir = File("target/fundings") / repositoryName
  if (outputDir.notExists)
    outputDir.createDirectories()

  val store = File("/Volumes/narcis-store")
  val repository = store / repositoryName
  val fundingLongFiles = for {
    layer1 <- repository.children
    layer2 <- layer1.children
    publications <- layer2.children
    knawLong = publications / "knaw_long"
    if knawLong.exists
    if isFunding(knawLong)
    _ = println(knawLong)
    _ = knawLong copyTo (outputDir / s"${ publications.name }.xml")
  } yield knawLong

  fundingLongFiles.foreach(println)

  def isFunding(file: File): Boolean = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    ???
  }
}
