import scalaz.{-\/, \/, \/-}

object ElectionResults {
  //TODO Read and write from files
  //TODO need to handle possible number format exc
  //TODO need to check if sliding has lopped off a partial result and println it
  //TODO need to handle error if party code cannot be resolved to a name

  def parse(data: String): Error \/ Constituency = {
    val values = data.split(",").toList
    if (values.nonEmpty) {
      val partyVotes = values.tail.sliding(2, 2).map(
        partyVoteData => {
          val maybeParty = Parties.lookupByCode(partyVoteData.tail.head.trim)
          maybeParty.map(PartyVotes(partyVoteData.head.trim.toInt, _))
        }
      ).toList
     \/-(Constituency(values.head, partyVotes.flatten))
    } else {
      -\/(Error(s"Could not parse $values. It could be either empty or not comma delimited"))
    }
  }
}
