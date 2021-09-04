import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080/")
    .acceptHeader("application/json")

  val scenario1 = scenario("API 1")
    .during(30 seconds) {
      exec(http("request")
        .get("/api1"))
        .pause(0, 1)
    }

  val scenario2 = scenario("API 2")
    .during(30 seconds) {
      exec(http("request")
        .get("/api2"))
        .pause(0, 1)
    }

  val scenario3 = scenario("API 3")
    .during(30 seconds) {
      exec(http("request")
        .get("/api3"))
        .pause(0, 1)
    }

  setUp(
    scenario1.inject(rampUsers(10) during (5 seconds)),
    scenario2.inject(rampUsers(100) during (10 seconds)),
    scenario3.inject(atOnceUsers(10))
  ).protocols(httpProtocol).assertions(
    global.successfulRequests.percent.is(100)
  )
}
