package utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Environment {

  val baseURL = "https://pcs.#{env}.platform.hmcts.net"
  val idamURL = "https://idam-web-public.#{env}.platform.hmcts.net"

  val minThinkTime = 5
  val maxThinkTime = 7

}
