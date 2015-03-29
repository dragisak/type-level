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

## List [+A, Size <: Nat]

List with size information encoded in it's type:
 
```scala
import com.dragisak.typelevel.List._
import com.dragisak.typelevel.Nat._

val l = 1 :: 2 :: 3 :: Nil

val l3 :List[Int, Nat3] = l

// does not compile, requires list of size 2
val l2 :List[Int, Nat2] = l

```
