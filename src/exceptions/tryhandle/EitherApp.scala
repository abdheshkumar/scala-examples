package exceptions.tryhandle

/**
 * Created by abdhesh on 7/22/15.
 */

case class User(id: Int, name: String, password: String)

case class ServiceResponse(status: Int, message: String)

class ServiceClient(val users: Map[Int, User]) {

  def userDetails(id: Int, password: String): Either[ServiceResponse, User] = {

    users.get(id) match {
      case Some(user) => if (user.password == password) {
        Right(user)
      } else {
        Left(ServiceResponse(401, "You can not view this user's details"))
      }
      case None => Left(ServiceResponse(404, "User does not exist"))
    }

  }

}

object EitherApp extends App {

  val service = new ServiceClient(
    Map(1 -> User(1, "John Doe", "123456"))
  )
  val message = service.userDetails(1, "none").fold(
    response => {
      s"Password does not match ${response.status} - ${response.message}"
    }, user => {
      s"User is ${user.name}"
    })

  service.userDetails(1, "none").left.foreach(response => s"Password does not match ${response.status} - ${response.message}")
  service.userDetails(1, "none").left.map(response => s"Password does not match ${response.status} - ${response.message}")

  service.userDetails(1, "none").right.foreach(user => s"User is ${user.name}")

  service.userDetails(1, "none").right.map(user => s"User is ${user.name}")
}
