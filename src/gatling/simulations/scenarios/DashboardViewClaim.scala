package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object DashboardViewClaim {

  val BaseURL = Environment.baseURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader

  val ViewTheClaim =

    group("PossessionClaims_004_ViewTheClaim") {

      exec(http("PossessionClaims_004_005_ViewTheClaim")
        .get(BaseURL + "/case/#{caseId}/view-the-claim")   //#{caseId}
        .headers(CommonHeader)
        .check(substring("Date submitted"))
        .check(substring("Statement of truth"))
        .check(substring("Case number:"))) 

    }

    .pause(MinThinkTime, MaxThinkTime)
}