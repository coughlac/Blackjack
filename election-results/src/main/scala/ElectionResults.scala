
object ElectionResults {
  def parse(data: String): Constituency =
    Constituency(data.split(",").toList.head)
}
