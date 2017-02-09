/**
  * Created by Effi on 2/2/2017.
  */
case class Catalog(productWeights: Array[Int]) {
  def getWeight(productId: Int): Int = productWeights(productId)
}