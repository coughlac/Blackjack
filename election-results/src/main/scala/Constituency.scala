import scala.math.BigDecimal.RoundingMode

case class Constituency(name: String, partyVotes: List[PartyVotes]) {
  lazy val total: Int = partyVotes.foldLeft(0)((acc, partyVotes) => acc + partyVotes.votes)
  def getPercentageForPartyCode(code: String): String = partyVotes.find(_.party.code == code)
    .fold("No results found. Please check code entered is correct.")(_.percentage(total).toString())
}

case class Error(message: String)

case class PartyVotes(votes: Int, party: Party){
  def percentage(total: Int): BigDecimal = (BigDecimal(votes) / total * 100).setScale(2, RoundingMode.HALF_UP)
}
