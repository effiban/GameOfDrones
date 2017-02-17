/**
  * Created by Effi on 2/2/2017.
  */
case class Warehouse(id: Int,
                     location: Point,
                     var totalInventory: Inventory,
                     var inventoryToBeRemoved: Inventory) {

  def getNumProducts(productId: Int): Int = totalInventory.getNumProducts(productId)

  def getAvailableInventoryFrom(inventory: Inventory): Inventory = {
    (this.totalInventory - this.inventoryToBeRemoved).getAvailableFrom(inventory)
  }

  def +=(inventory: Inventory): Unit = this.totalInventory += inventory
  def -=(inventory: Inventory): Unit = this.totalInventory -= inventory


}
