package metadata

import com.dragisak.typelevel.HList.HNil
import com.dragisak.typelevel.{<<:, HList}
import com.dragisak.typelevel.HList._

import scala.collection.IndexedSeqView.Id

sealed trait FieldType

object FieldType {

  sealed trait Id                   extends FieldType
  sealed trait Text                 extends FieldType
  sealed trait Flag                 extends FieldType
  sealed trait Money                extends FieldType
  sealed trait Coll[T <: FieldType] extends FieldType

}

sealed trait Mandatory

object Mandatory {
  sealed trait Yes extends Mandatory
  sealed trait No  extends Mandatory
}

trait FieldDef[T <: FieldType, M <: Mandatory] {

  type Def = this.type

  def name: String
}

object FieldDef {
  def apply[T <: FieldType, M <: Mandatory](n: String): FieldDef[T, M] = new FieldDef[T, M] {
    override val name: String = n
  }
}

trait EntityDef[Field <: HList, FieldGroup <: FieldDef[_, _]] {

  type Def = this.type

  def name: String
}

object EntityDef {
  def apply[Fields <: HList, FieldGroup <: FieldDef[_, _]](n: String)(
      implicit ev1: Fields <<: FieldGroup
  ): EntityDef[Fields, FieldGroup] = new EntityDef[Fields, FieldGroup] { override val name: String = n }
}

object Metadata {

  trait Entity {
    sealed trait FieldGroup[T <: FieldType, M <: Mandatory] extends FieldDef[T, M]

    protected def field[T <: FieldType, M <: Mandatory](n: String): FieldGroup[T, M] = new FieldGroup[T, M] {
      override val name: String = n
    }

    type Fields <: HList
    final type Def = EntityDef[Fields, FieldGroup[_, _]]

    protected def entity(n: String)(implicit ev: Fields <<: FieldGroup[_, _]): Def = EntityDef(n)

    def value: Def
  }

//  def entityValue[T <: Entity](implicit ev: T#Fields <<: T#FieldGroup): T#Def = EntityDef[T#Fields, T#FieldGroup](ev)

  object Organization extends Entity {
    val Id       = field[FieldType.Id, Mandatory.Yes]("id")
    val Name     = field[FieldType.Text, Mandatory.Yes]("name")
    val Founders = field[FieldType.Coll[FieldType.Id], Mandatory.No]("founders")

    override final type Fields = Id.Def :: Name.Def :: Founders.Def :: HNil

    override val value: Def = entity("organization")
  }

  object FundingRound extends Entity {
    val Id        = field[FieldType.Id, Mandatory.Yes]("id")
    val Round     = field[FieldType.Text, Mandatory.Yes]("round")
    val Amount    = field[FieldType.Money, Mandatory.Yes]("amount")
    val Investors = field[FieldType.Coll[FieldType.Id], Mandatory.No]("investors")
    val Investee  = field[FieldType.Id, Mandatory.No]("investee")

    override final type Fields = Id.Def :: Round.Def :: Amount.Def :: Investee.Def :: Investors.Def :: HNil

    override val value: Def = entity("funding_round")
  }
}
