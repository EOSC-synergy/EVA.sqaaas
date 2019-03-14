package nl.knaw.dans.narcis.cerif

import better.files.File

import scala.xml.{ Utility, XML }

/**
 * Iterate through the 'patent's found by `SearchPatents.scala` and perform queries on them 
 */
object ReadPatents extends App {
  val xmlNamespace = "http://www.w3.org/XML/1998/namespace"
  val repositoryName = "tue"

  val patents = File("target/patents") / repositoryName
  require(patents.exists)

  val knawLongFiles = patents.children

  //  knawLongFiles.take(100).foreach(findTitleInfo)
  //  knawLongFiles.take(100).foreach(findLanguage)
  //  knawLongFiles.take(100).foreach(findRelatedItemType)
  knawLongFiles.take(100).foreach(findPublicationDate)
  //  knawLongFiles.take(100).foreach(findRelatedItemPart)
  //  println(knawLongFiles.take(100).flatMap(findPublicationIdentifierTypes).toSet)
  //  knawLongFiles.take(100).foreach(findISBN)
  //  knawLongFiles.take(100).foreach(findLocationURL)
  //  knawLongFiles.take(100).foreach(findRelatedItemISSN)
  //  knawLongFiles.take(100).foreach(findSubject)
  //  knawLongFiles.take(100).foreach(findAbstract)
  //  knawLongFiles.take(100).foreach(findPhysicalDescription)
  //  knawLongFiles.take(100).foreach(findRightsDescription)
  //  println(knawLongFiles.take(100).flatMap(findAccessRight).toSet)
  //  knawLongFiles.take(100).flatMap(findGenre).foreach(println)
  //  println(knawLongFiles.take(100).flatMap(findGenre).toSet)

  def findTitleInfo(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    val titleInfos = xml \ "metadata" \ "titleInfo"
    for (titleInfo <- titleInfos;
         namespace = titleInfo.attribute(xmlNamespace, "lang")) {
      val title = (titleInfo \ "title").text
      val subtitle = (titleInfo \ "subtitle").text

      println((namespace, title, subtitle))
    }
  }

  def findLanguage(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    val lang = xml \ "metadata" \ "language"
    val text = lang.text
    if (text.isEmpty)
      println("none")
    else if (text == "und") {
      println(s"${ file.parent } - $text")
    }
    else
      println(text)
  }

  def findRelatedItemType(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    val `type` = xml \ "metadata" \ "relatedItem" \@ "type"
    if (`type`.isEmpty)
      println(s"${ file.parent } - no type attribute")
    else if (`type` == "host") {
      println("host")
    }
    else
      println(s"${ file.parent } - ${ `type` }")
  }

  def findPublicationDate(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    val dateIssued = (xml \ "metadata" \ "dateIssued" \ "parsed").text
    //    val dateSubmitted = (xml \ "metadata" \ "dateSubmitted" \ "parsed").text
    //    val dateAccepted = (xml \ "metadata" \ "dateAccepted" \ "parsed").text
    //    val dateCreated = (xml \ "metadata" \ "dateCreated" \ "parsed").text
    //    val dateUpdated = (xml \ "metadata" \ "dateUpdated" \ "parsed").text
    //    val dateAvailable = (xml \ "metadata" \ "dateAvailable" \ "parsed").text
    //    val dateCopyrighted = (xml \ "metadata" \ "dateCopyrighted" \ "parsed").text
    //    val dateValid = (xml \ "metadata" \ "dateValid" \ "parsed").text
    //    val dateCollected = (xml \ "metadata" \ "dateCollected" \ "parsed").text

    println(dateIssued)
  }

  def findRelatedItemPart(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    val part = xml \ "metadata" \ "relatedItem" \ "part"
    println(part)
  }

  def findPublicationIdentifierTypes(file: File): Seq[String] = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "metadata" \ "publication_identifier").map(_ \@ "type")
  }

  def findISBN(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "publication_identifier"
         if elem \@ "type" == "isbn";
         text = elem.text) {
      println(text)
    }
  }

  def findLocationURL(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "metadata" \ "location_url").map(_.text).foreach(println)
  }

  def findRelatedItemISSN(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "relatedItem" \ "publication_identifier"
         if elem \@ "type" == "issn";
         text = elem.text) {
      println(text)
    }
  }

  def findSubject(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "subject" \ "topic" \ "topicValue") {
      println(elem)
    }
  }

  def findAbstract(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "abstract") {
      println(elem.text)
    }
  }

  def findPhysicalDescription(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "physicalDescription") {
      println(elem.text)
    }
  }

  def findRightsDescription(file: File): Unit = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    for (elem <- xml \ "metadata" \ "rightsDescription") {
      println(elem.text)
    }
  }

  def findAccessRight(file: File): Seq[String] = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "accessRights").map(_.text)
  }

  def findGenre(file: File): Seq[String] = {
    val xml = Utility.trim(XML.loadFile(file.toJava))

    (xml \ "metadata" \ "genre").map(_.text)
  }
}
