
sealed trait Party {
  val code: String
  val name: String
}
case object Conservatives extends Party {
  val code = "C"
  val name = "Conservative Party"
}

case object Labour extends Party {
  val code = "L"
  val name = "Labour Party"
}

case object UKIP extends Party {
  val code = "UKIP"
  val name = "UKIP"
}

case object LibDems extends Party {
  val code = "LD"
  val name = "Liberal Democrats"
}

case object Greens extends Party {
  val code = "G"
  val name = "Green Party"
}

case object Independents extends Party {
  val code = "Ind"
  val name = "Independent"
}

case object SNP extends Party {
  val code = "SNP"
  val name = "SNP"
}

object Parties {
  private val all: Set[Party] = Set(Conservatives, Labour, UKIP, LibDems, Greens, Independents, SNP)
  def lookupByCode(code: String): Option[Party] = all.find(_.code == code)
}