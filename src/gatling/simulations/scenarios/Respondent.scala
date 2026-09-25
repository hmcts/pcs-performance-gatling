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

    group("PossessionClaims_060_CheckBeforeStart") {

      exec(http("PossessionClaims_060_005_CheckBeforeStart")
        .get(BaseURL + "/case/#{caseId}/respond-to-claim/start-now")
        .headers(CommonHeader)
        .check(CsrfCheck.save)
        .check(substring("Respond to a property possession claim online"))
        .check(substring("Support to use HMCTS services")))
      
    }

    .pause(MinThinkTime, MaxThinkTime)

    .group("PossessionClaims_070_CheckBeforeStartSubmit") {

      exec(http("PossessionClaims_070_005_CheckBeforeStartSubmit")
          .post(BaseURL + "/case/#{caseId}/respond-to-claim/start-now")
          .headers(CommonHeader)
          .headers(PostHeader)
          .formParam("_csrf", "#{csrf}")
          .check(substring("You’re entitled to free legal advice"))
          .check(substring("Have you had any free legal advice?")))
      }

    .pause(MinThinkTime, MaxThinkTime)

    .group("PossessionClaims_080_FreeLegalAdvice") {

      exec(http("PossessionClaims_080_005_FreeLegalAdvice")
          .post(BaseURL + "/case/#{caseId}/respond-to-claim/free-legal-advice?nav=1")
          .headers(CommonHeader)
          .headers(PostHeader)
          .formParam("hadLegalAdvice", "no")
          .formParam("action", "continue")
          .formParam("_csrf", "#{csrf}")
          .check(substring("Do you have a solicitor?")))
      }

    .pause(MinThinkTime, MaxThinkTime)

    .group("PossessionClaims_090_Solicitor") {

      exec(http("PossessionClaims_090_005_Solicitor")
          .post(BaseURL + "/case/#{caseId}/respond-to-claim/solicitor?nav=1")
          .headers(CommonHeader)
          .headers(PostHeader)
          .formParam("hasSolicitor", "NO")
          .formParam("action", "continue")
          .formParam("_csrf", "#{csrf}")
          .check(substring("Check your answers"))
          .check(substring("Free legal advice")))
      }
      
    .pause(MinThinkTime, MaxThinkTime)

    .group("PossessionClaims_100_CheckYourAnswersSubmit") {

      exec(http("PossessionClaims_100_005_CheckYourAnswersSubmit")
          .post(BaseURL + "/case/#{caseId}/respond-to-claim/check-your-answers-start-now-and-details")
          .headers(CommonHeader)
          .headers(PostHeader)
          .formParam("action", "continue")
          .formParam("_csrf", "#{csrf}")
          .check(substring("Read information about responding and free legal advice<span class=\"govuk-visually-hidden\"> &ndash; Done</span>"))
          .check(substring("Review and submit")))
      }
      
    .pause(MinThinkTime, MaxThinkTime)


}