package controllers

import javax.inject._

import actors.UserActor.PrintUser
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import model.Register
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(@Named("user-supervisor") userSupervisor: ActorRef,
                               system: ActorSystem,
                               cc: ControllerComponents)
                              (implicit ec: ExecutionContext) extends AbstractController(cc) {
  implicit val timeout: Timeout = Timeout(1.minutes)

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action.async {
    //    val userSupervisor = system.actorOf(UserSupervisorActor.props, "userSupervisor")

    //    println(system.actorSelection("userSupervisor"))

    userSupervisor ! Register("moguzhanataman@gmail.com", Option("hello"))
    userSupervisor ! Register("deneme@example.com", Option("pass"))

    val actRefs = system.actorSelection("userSupervisor/*")

    val userDatas = actRefs ? PrintUser

    userDatas.mapTo[String].map { message =>
      Ok(message)
    }

    //    Ok(views.html.index())
  }
}
