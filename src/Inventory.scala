/**
  * Created by Effi on 2/2/2017.
  */
case class Inventory(catalog: Catalog,
                     var productAmounts: Array[Int]) {

  def this(catalog: Catalog) = {
    this(catalog, Array.fill(catalog.productWeights.length)(0))
  }

  def getNumProducts(productId: Int): Int = productAmounts(productId)

  def getWeight: Int = {
    productAmounts.zipWithIndex
      .map(pair => getWeight(pair)).sum
  }

  def + (inventory: Inventory): Inventory = {
    val newInv = inventory.copy()
    for (i <- 1 to catalog.productWeights.length) {
      newInv.productAmounts(i) + inventory.productAmounts(i)
    }
    newInv
  }

  def += (inventory: Inventory): Unit = {
    for (i <- 1 to catalog.productWeights.length) {
      productAmounts(i) += inventory.productAmounts(i)
    }
  }

  def - (inventory: Inventory): Inventory = {
    val newInv = inventory.copy()
    for (i <- 1 to catalog.productWeights.length) {
      newInv.productAmounts(i) - inventory.productAmounts(i)
    }
    newInv
  }

  def -= (inventory: Inventory): Unit = {
    for (i <- 1 to catalog.productWeights.length) {
      productAmounts(i) -= inventory.productAmounts(i)
    }
  }

  def getAvailableFrom(inventory: Inventory): Inventory = null

  def isEmpty: Boolean = productAmounts.forall(_ == 0)

  private def getWeight(numProductsToId: (Int, Int)): Int = {
    numProductsToId match {
      case (num, id) => num * catalog.getWeight(id)
    }
  }
}
