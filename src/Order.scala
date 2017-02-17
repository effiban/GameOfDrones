/**
  * Created by Effi on 2/2/2017.
  */
case class Order(location: Point,
                 totalInventory: Inventory,
                 var enRouteInventory: Inventory,
                 var deliveredInventory: Inventory) {
  def isFulfilled: Boolean = totalInventory == (deliveredInventory + enRouteInventory)

  def deliver(inventory: Inventory): Unit = deliveredInventory += inventory

  def missingInventory: Inventory = enRouteInventory - totalInventory
}
