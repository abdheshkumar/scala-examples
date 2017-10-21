package caseclasses

/**
 * Created by abdhesh on 6/25/15.
 */
class Pizza(var crustType: String) {
  override def toString = "Crust type is " + crustType
}

// companion object
object Pizza {
  val CRUST_TYPE_THIN = "thin"
  val CRUST_TYPE_THICK = "thick"

  def getFoo = "Foo"
}