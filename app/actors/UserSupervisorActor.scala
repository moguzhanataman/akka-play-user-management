package actors

import actors.UserActor.InitUser
import akka.actor._
import model.Register

class UserSupervisorActor extends Actor {

  override def receive = {

    // Register user: Create new user actor
    case Register(email: String,
                  pass: Option[String]) => {
      val userActor = context.actorOf(UserActor.props(email), email)
      userActor ! InitUser(email)
    }

  }
}

object UserSupervisorActor {
  def props = Props[UserSupervisorActor]
}