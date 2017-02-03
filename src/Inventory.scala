/**
  * Created by Effi on 2/2/2017.
  */
case class Inventory(catalog: Catalog,
                     productAmounts: Seq[Int]) {
  def getNumProducts(productId: Int): Int = productAmounts(productId)

  def getWeight: Int = {
    productAmounts.zipWithIndex.reduce(
      (pair1, pair2) => getWeight(pair1) + getWeight(pair2)
    )
  }

  def addProducts(productId: Int, amount: Int): Inventory = {
    copy(productAmounts = productAmounts.updated(productId, productAmounts(productId) + amount))
  }

  private def getWeight(numProductsToId: (Int, Int)): Int = {
    numProductsToId match {
      case (num, id) => num * catalog.getWeight(id)
    }
  }
}