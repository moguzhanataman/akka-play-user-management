package services

import actors.UserSupervisorActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class UserSupervisorModule extends AbstractModule with AkkaGuiceSupport {
  override def configure = bindActor[UserSupervisorActor]("user-supervisor")
}
