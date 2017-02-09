

/**
  * Created by Effi on 2/2/2017.
  */
case class Drone(var maxWeight: Int,
                 var inventory: Inventory,
                 var maybeActiveCommand: Option[Command],
                 var destination: Point,
                 var turnsLeft: Int) {

  def remainingWeight: Int = maxWeight - inventory.getWeight

  def addProducts(productId: Int, amount: Int): Unit = inventory.addProducts(productId, amount)

  def move(): Unit = {
    (turnsLeft, maybeActiveCommand) match {
      case (turn, Some(cmd)) if turn > 0 => turnsLeft -= 1
    }
  }
}
