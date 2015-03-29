# Some examples of type level programming in Scala

## HList

Heterogeneous list:

```scala
import com.dragisak.typelevel.HList._

val l = false :: 2 :: Some(4) :: "foo" :: HNil // HCons(false,HCons(2,HCons(Some(4),HCons(foo,HNil))))
```
