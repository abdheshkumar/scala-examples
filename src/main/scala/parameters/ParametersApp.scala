package parameters

/**
 * Created by abdhesh on 6/25/15.
 */
object ParametersApp {

  class Socket(val timeout: Int = 1000, val linger: Int = 2000) {
    override def toString = s"timeout: $timeout, linger: $linger"
  }

  println(new Socket)
  println(new Socket(3000))
  println(new Socket(3000, 4000))

  //Using named parameters
  println(new Socket(timeout = 3000, linger = 4000))
  println(new Socket(linger = 4000, timeout = 3000))
  println(new Socket(timeout = 3000))
  println(new Socket(linger = 4000))

  def makeConnection(timeout: Int = 5000, protocol: String = "http") {
    println("timeout = %d, protocol = %s".format(timeout, protocol))
    // more code here
  }

  makeConnection()
  makeConnection(2000)
  makeConnection(3000, "https")


  class Pizza {
    var crustSize = 12
    var crustType = "Thin"

    def update(crustSize: Int, crustType: String) {
      this.crustSize = crustSize
      this.crustType = crustType
    }

    override def toString = {
      "A %d inch %s crust pizza.".format(crustSize, crustType)
    }
  }

  val p = new Pizza

  p.update(crustSize = 16, crustType = "Thick")

  p.update(crustType = "Pan", crustSize = 14)

}
