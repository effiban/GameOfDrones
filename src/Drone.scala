

/**
  * Created by Effi on 2/2/2017.
  */
case class Drone(maxWeight: Int,
                 inventory: Inventory,
                 maybeActiveCommand: Option[Command],
                 destination: Point,
                 turnsLeft: Int) {

  def remainingWeight: Int = maxWeight - inventory.getWeight

  def addProducts(productId: Int, amount: Int): Drone = copy(inventory = inventory.addProducts(productId, amount))
}
