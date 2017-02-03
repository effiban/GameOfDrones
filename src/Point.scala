import scala.math.{abs, ceil, pow, sqrt}

/**
  * Created by Effi on 2/2/2017.
  */
case class Point(row: Int, col: Int) {
  def distance(other: Point): Int = ceil(sqrt(pow(row - other.row, 2) + pow(col - other.col, 2))).toInt
}
