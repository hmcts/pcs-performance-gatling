package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object Homepage {

  val BaseURL = Environment.baseURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader

  val PossessionClaimsServiceHomepage =

    exec(flushHttpCache)
    .exec(flushCookieJar)

    .group("PossessionClaims_010_HomePage") {

      exec(http("PossessionClaims_010_005_HomePage")
        .get(BaseURL + "/")
        .headers(CommonHeader)
        .header("sec-fetch-site", "none")
        .check(regex("""nonce=([\w=-]+)&amp;response_type""").saveAs("nonce"))
        .check(regex("""code_challenge=([\w=-]+)&amp;code_challenge_method""").saveAs("codeChallenge"))
        .check(regex("""code_challenge_method=([\w-]+)"""").saveAs("codeChallengeMethod"))
        .check(CsrfCheck.save)
        .check(substring("Sign in or create an account")))
    }
    .pause(MinThinkTime, MaxThinkTime)
}