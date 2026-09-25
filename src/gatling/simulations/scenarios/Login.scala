package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object Login {

  val BaseURL = Environment.baseURL
  val IdamURL = Environment.idamURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader
  val PostHeader = Headers.postHeader

  val PossessionClaimsServiceLogin =

    group("PossessionClaims_020_Login") {

      exec(http("PossessionClaims_020_005_Login")
        .post(IdamURL + s"/login?client_id=pcs-frontend&redirect_uri=${BaseURL}/oauth2/callback&nonce=#{nonce}&response_type=code&scope=openid%20profile%20roles&code_challenge=#{codeChallenge}&code_challenge_method=#{codeChallengeMethod}")
        .headers(CommonHeader)
        .headers(PostHeader)
        .formParam("username", "citizen101@test.perftest") //#{emailAddress}
        .formParam("password", "Pa$$w0rd") //#{password}
        .formParam("selfRegistrationEnabled", "true")
        .formParam("_csrf", "#{csrf}")
        .check(substring("Your Possession Claims account")))

    }

    .pause(MinThinkTime, MaxThinkTime)
}