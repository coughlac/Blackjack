import org.scalatest.{FreeSpec, ShouldMatchers}

class ResultsSpec extends FreeSpec with ShouldMatchers {
  "Get constituency name from result" in {
    val data = "Cardiff West, 11014, C, 17803, L, 4923, UKIP, 2069, LD"
    val constituency = ElectionResults.parse(data).getOrElse(fail("Expected to get results back"))
    constituency.name shouldBe "Cardiff West"

    val nextConstituencyData = "Islington South & Finsbury, 22547, L, 9389, C, 4829, LD, 3375, UKIP, 3371, G, 309, Ind"
    val nextConstituency = ElectionResults.parse(nextConstituencyData).getOrElse(fail("Expected to get results back"))
    nextConstituency.name shouldBe "Islington South & Finsbury"
  }

  "Get total votes for constituency" in {
    val data = "Cardiff West, 11014, C, 17803, L, 4923, UKIP, 2069, LD"
    val constituency = ElectionResults.parse(data).getOrElse(fail("Expected to get results back"))
    constituency.total shouldEqual (11014 + 17803 + 4923 + 2069)
  }

  "Show the share of the votes of a party over all the votes cast for the constituency" in {
    val data = "Cardiff West, 11014, C, 17803, L, 4923, UKIP, 2069, LD"
    val constituency = ElectionResults.parse(data).getOrElse(fail("Expected to get results back"))
    constituency.getPercentageForPartyCode("C") shouldEqual "30.76"
  }
}
