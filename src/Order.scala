/**
  * Created by Effi on 2/2/2017.
  */
case class Order(location: Point,
                 requestedInventory: Inventory,
                 var deliveredInventory: Inventory) {
  def isFulfilled: Boolean = requestedInventory == deliveredInventory

  def deliverProducts(productId: Int, amount: Int): Unit = deliveredInventory.addProducts(productId, amount)
}
