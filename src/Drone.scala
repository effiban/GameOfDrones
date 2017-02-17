

/**
  * Created by Effi on 2/2/2017.
  */
case class Drone(id: Int,
                 var maxWeight: Int,
                 var inventory: Inventory,
                 var cmds: Seq[Command],
                 var destination: Point,
                 var turnsLeft: Int) {

  def remainingWeight: Int = maxWeight - inventory.getWeight

  def + (inventory: Inventory): Unit = this.inventory += inventory
  def - (inventory: Inventory): Unit = this.inventory -= inventory

  def move(): Unit = {
    (turnsLeft, cmds) match {
      case (turn, cmd :: _) if turn > 0 => turnsLeft -= 1
    }
  }

  def getAvailableInventoryFrom(inventory: Inventory): Inventory = null
}
