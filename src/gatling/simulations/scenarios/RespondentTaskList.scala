package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object RespondentTaskList {

  val BaseURL = Environment.baseURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader

  val RespondToTheClaim =

    group("PossessionClaims_005_RespondToTheClaim") {

      exec(http("PossessionClaims_005_005_RespondToTheClaim")
        .get(BaseURL + "/case/#{caseId}/respond-to-claim/task-list")
        .headers(CommonHeader)
        .check(substring("1. Check before you start"))
        .check(substring("4. Review and submit"))
        .check(substring("Case number:"))) 

    }

    .pause(MinThinkTime, MaxThinkTime)
}