# 아이템 4. inferred 타입으로 리턴하지 말라.

- inferred 타입은 정확하게 오른쪽에 있는 피연산자에 맞게 설정된다.
- return type 은 외부에서 확인할 수 있게 명시적으로 지정해야 한다.

```kotlin
open class Animal 
class Zebra: AnimaL()

val animal = Zebra()
animal = Animal() // Type mismatch

val animal : Animal = Zebra()
animal = Animal() // OK!


```


