import org.scalatest._
import org.scalatest.prop.PropertyChecks._
import org.scalatest.prop.Tables.Table

class PartiesSpec extends FreeSpec with ShouldMatchers {
  "Translate party code into full party name" in {
    val properties = Table(
      ("C", "Conservative"),
      ("L", "Labour Party"),
      ("UKIP", "UKIP"),
      ("LD", "Liberal Democrats"),
      ("G", "Green Party"),
      ("SNP", "SNP"),
      ("Ind", "Independent")
    )
    forAll(properties) {
      (code, expectedName) =>
        Parties.lookupByCode(code).getOrElse(fail(s"No party recognised for: $code")).name should be(expectedName)
    }
  }
}
