sealed trait Result

case object IN_PLAY extends Result

case object STOPPED extends Result

sealed trait EndResult extends Result

case object BLACKJACK extends EndResult

case object WON extends EndResult

case object LOST extends EndResult



