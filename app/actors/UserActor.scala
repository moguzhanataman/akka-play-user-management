package actors

import actors.UserActor._
import akka.actor._
import model.User

// User must have email, everything else will be recoverable & editable
class UserActor(email: String) extends Actor {

  var userData: User = _

  override def receive = {
    case InitUser(email) => userData = User(email)
    case SetName(name) => userData.copy(name = Option(name))
    case SetPass(pass) => userData.copy(pass = Option(pass))
    case SetEmail(email) => userData.copy(email = email)
    case SetAdmin(isAdmin) => userData.copy(isAdmin = isAdmin)
    case SetPersonnel(isPersonnel) => userData.copy(isPersonnel = isPersonnel)
    case SetVerified(isVerified) => userData.copy(isVerified = isVerified)
    case PrintUser => sender ! ("" + userData.email + " " + userData.name.getOrElse("no-name"))
    case _ =>
  }
}

object UserActor {
  def props(email: String): Props = Props(new UserActor(email))

  case class InitUser(email: String)
  case class SetName(name: String)
  case class SetPass(pass: String)
  case class SetEmail(email: String)
  case class SetAdmin(isAdmin: Boolean = false)
  case class SetPersonnel(isPersonnel: Boolean = false)
  case class SetVerified(isVerified: Boolean = false)

  case object PrintUser
}
