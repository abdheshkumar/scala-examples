package commons

/**
 * Created by abdhesh on 6/17/15.
 */

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

object EnumApp {

  def main(array: Array[String]) = {
    import WeekDay._

    def isWorkingDay(d: WeekDay) = !(d == Sat || d == Sun)

    WeekDay.values filter isWorkingDay foreach println
  }
}
