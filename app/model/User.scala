package model

case class User(var email: String,
                var name: Option[String] = None,
                var pass: Option[String] = None,

                var isAdmin: Boolean = false,
                var isPersonnel: Boolean = false,
                var isVerified: Boolean = false)
