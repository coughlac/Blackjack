
sealed trait Result

case object NOT_BLACKJACK extends Result

sealed trait WIN extends Result

case object BLACKJACK extends WIN

