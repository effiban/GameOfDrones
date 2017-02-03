/**
  * Created by Effi on 2/2/2017.
  */
case class Warehouse(location: Point,
                     inventory: Inventory) {

  def getNumProducts(productId: Int): Int = inventory.getNumProducts(productId)

  def addProducts(productId: Int, amount: Int): Warehouse = copy(inventory = inventory.addProducts(productId, amount))
}
