/**
  * Created by Effi on 2/2/2017.
  */
case class Order(location: Point,
                 requestedInventory: Inventory,
                 deliveredInventory: Inventory) {
  def isFulfilled: Boolean = requestedInventory == deliveredInventory

  def deliverProducts(productId: Int, amount: Int): Order = copy(deliveredInventory = deliveredInventory.addProducts(productId, amount))
}
