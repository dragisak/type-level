package metadata

import com.dragisak.typelevel.HList.HNil
import com.dragisak.typelevel.{<<:, HList}
import com.dragisak.typelevel.HList._

sealed trait FieldType

object FieldType {

  sealed trait Id                   extends FieldType
  sealed trait Text                 extends FieldType
  sealed trait Flag                 extends FieldType
  sealed trait Coll[T <: FieldType] extends FieldType

}

sealed trait Mandatory

object Mandatory {
  sealed trait Yes extends Mandatory
  sealed trait No  extends Mandatory
}

trait FieldDef[Type <: FieldType, M <: Mandatory]

trait EntityDef[Field <: HList, FieldGroup <: FieldDef[_, _]]

object EntityDef {
  def apply[Fields <: HList, FieldGroup <: FieldDef[_, _]](
      implicit ev1: Fields <<: FieldGroup
  ): EntityDef[Fields, FieldGroup] = null
}

object Metadata {

  trait Entity {
    type FieldGroup <: FieldDef[_, _]
    type Fields <: HList
    final type Def = EntityDef[Fields, FieldGroup]
    def value: Def
  }

//  def entityValue[T <: Entity](implicit ev: T#Fields <<: T#FieldGroup): T#Def = EntityDef[T#Fields, T#FieldGroup](ev)

  object Organization extends Entity {
    sealed trait OrgField[T <: FieldType, M <: Mandatory] extends FieldDef[T, M]
    override type FieldGroup = OrgField[_, _]
    type Id                  = OrgField[FieldType.Id, Mandatory.Yes]
    type Name                = OrgField[FieldType.Text, Mandatory.Yes]
    type Founders            = OrgField[FieldType.Coll[FieldType.Id], Mandatory.No]
    override type Fields     = Id :: Name :: Founders :: HNil
    override val value: Def = EntityDef[Fields, FieldGroup]
  }

}
