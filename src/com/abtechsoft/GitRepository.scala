package com.abtechsoft

/**
 * Created by abdhesh on 6/11/15.
 */
object GitRepository {

  def githubRss(user: String, repo: String, branch: String): String = {
    val url = s"https://github.com/$user/$repo/commits/$branch.atom"
    val lines = io.Source.fromURL(url).getLines.toList
    val xml = lines.map(_.trim).mkString("")
    xml
  }

  def xmlToEntryList(xml: String) = xml.split("</?entry>").filterNot(_.isEmpty).tail

  def child(xml: String, name: String): Option[String] = {
    val p = s".*<$name>(.*)</$name>.*".r
    xml match {
      case p(result) => Option(result)
      case _ => None
    }
  }

  def report(entryXml: String): Option[String] = {
    for {
      title <- child(entryXml, "title")
      date <- child(entryXml, "updated").map(_.replaceAll("T.*", ""))
      author <- child(entryXml, "name")
    }
      yield s"title:  $title\ndate:   $date\nauthor: $author"
  }

  def getGithubReport(user: String, repo: String, branch: String): String = {
    val xml = githubRss(user, repo, branch)
    val entries = xmlToEntryList(xml).toList
    val formattedEntries = entries flatMap report
    val title = s"Github commit activity for $repo:$branch"
    title :: formattedEntries mkString ("\n" + "=" * 80 + "\n")
  }

  val slickReport = getGithubReport("slick", "slick", "master")
}
