
object ElectionResults {
  def parse(data: String): ConstituencyResult = {
    val values: List[String] = data.split(",").toList
    ConstituencyResult(values.head)
  }
}
