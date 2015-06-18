package session1

/**
 * Created by abdhesh on 6/17/15.
 */
object CommonApp extends App {

  val songTitles = List("The White Hare", "Childe the Hunter", "Take no Rogues")
  songTitles.map(t => t.toLowerCase)
  songTitles.map(_.toLowerCase)

  val wordFrequencies = ("habitual", 6) ::("and", 56) ::("consuetudinary", 2) ::
    ("additionally", 27) ::("homely", 5) ::("society", 13) :: Nil

  def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)

  wordsWithoutOutliers(wordFrequencies)

  def wordsWithoutOutliersWithMatch(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }

  val predicate: (String, Int) => Boolean = {
    case (_, f) => f > 3 && f < 25
  }

  val transformFn: (String, Int) => String = {
    case (w, _) => w
  }


  val pf = new PartialFunction[(String, Int), String] {
    def apply(wordFrequency: (String, Int)) = wordFrequency match {
      case (word, freq) if freq > 3 && freq < 25 => word
    }

    def isDefinedAt(wordFrequency: (String, Int)) = wordFrequency match {
      case (word, freq) if freq > 3 && freq < 25 => true
      case _ => false
    }
  }

  wordFrequencies.map(pf)
  wordFrequencies.collect(pf)


  def wordsWithoutOutliersWithCollect(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.collect { case (word, freq) if freq > 3 && freq < 25 => word }
}
