package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.{Environment, Headers, CsrfCheck}

object CitizenHub {

  val BaseURL = Environment.baseURL
  val IdamURL = Environment.idamURL

  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val CommonHeader = Headers.commonHeader
  val PostHeader = Headers.postHeader

  val ViewClaimDashboard =

    group("PossessionClaims_003_ViewClaimDashboard") {

      exec(http("PossessionClaims_003_005_ViewClaimDashboard")
        .get(BaseURL + "/case/#{caseId}/dashboard")   //#{caseId}
        .headers(CommonHeader)
        .check(substring("A property possession claim"))
        .check(substring("Case number:"))) 

    }

    .pause(MinThinkTime, MaxThinkTime)
}