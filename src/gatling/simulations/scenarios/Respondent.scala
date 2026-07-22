package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object Respondent {

  val BaseURL = Environment.baseURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader
  val PostHeader = Headers.postHeader

  val CheckBeforeStart =

    group("PossessionClaims_006_CheckBeforeStart") {

      exec(http("PossessionClaims_006_005_CheckBeforeStart")
        .get(BaseURL + "/case/1781215522091823/respond-to-claim/start-now")   //#{caseId}
        .headers(CommonHeader)
        .check(CsrfCheck.save)
        .check(substring("Respond to a property possession claim online"))
        .check(substring("Support to use HMCTS services")))
      
    }

    .pause(MinThinkTime, MaxThinkTime)

    group("PossessionClaims_007_CheckBeforeStartSubmit") {

      exec(http("PossessionClaims_007_005_CheckBeforeStartSubmit")
          .post(BaseURL + "/case/1781215522091823/respond-to-claim/start-now")   //#{caseId}
          .headers(CommonHeader)
          .formParam("_csrf", "#{csrf}")
          .check(substring("You’re entitled to free legal advice"))
          .check(substring("Have you had any free legal advice?")))
      }

    .pause(MinThinkTime, MaxThinkTime)
}