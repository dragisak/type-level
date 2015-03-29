# Some examples of type level programming in Scala

## HList

Heterogeneous list:

```scala
import com.dragisak.typelevel.HList._

val l = false :: 2 :: Some(4) :: "foo" :: HNil 
// HCons(false,HCons(2,HCons(Some(4),HCons(foo,HNil))))

val a = l.head
// a: Boolean = false

val b = l.tail.head
// b: Int = 2

```

## Nat

Natural numbers:
```scala
import com.dragisak.typelevel.Nat._

type x = Nat8#plus[Nat2] 
```
