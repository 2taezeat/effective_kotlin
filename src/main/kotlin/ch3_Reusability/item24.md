# 아이템 24. 제네릭 타입과 variance 한정자를 활용하라


- `out` 한정자는 타입 파라미터를 `covariant(공변성)`로 만듭니다.
- `in` 한정자는 타입 파라미터를 `contravariant(반병성)`로 만듭니다.


- `invariant(불공변성)`: 제네릭 타입으로 만들어지는 타입들이 서로 관련성이 없다는 의미 (`variance 한정자`가 없는 경우)
- `covariant(공변성)`: A가 B의 서브 타입일 때, `Cup<A>가 Cup<B>`의 서브타입이라는 의미 
- `contravariant(반병성)`: A가 B의 서브 타입일 때, `Cup<A>가 Cup<B>` 의 슈퍼타입이라는 의미

```kotlin
class Cup<T>

val b: Cup<Any> = Cup<Int>() // Error 
val a: Cup<Nothing> = Cup<Int>() // Error
```

```kotlin
class Cup<out T>
open class Dog
class Puppy: Dog

val b: Cup<Dog> = Cup<Puppy>() // OK 
val a: Cup<Puppy> = Cup<Dog>() // Error
```

```kotlin
class Cup<in T>
open class Dog
class Puppy: Dog

val b: Cup<Dog> = Cup<Puppy>() // Error
val a: Cup<Puppy> = Cup<Dog>() // OK
```

- 코틀린 함수 타입의 모든 파라미터는 `contravariant(반병성)`이다. return 타입은 `contravariant(out 한정자)` 이다. 
- 자바의 배열은 `invariant(불공변성)`이여서 문제가 발생한다.
```java
Integer[] numbers = {1,4,2,1};
Object[] objects = numbers;
objects[2] = "B"; // run-time Error
```
- 코틀린은 이러한 결함을 해결하기 위해 Array를 `invariant(불공변성)`로 만들었다.


- 코틀린에서 `List와 Set` 타입 파라미터는 `covariant(out 한정자)`
- `Map`에서 값의 타입을 나타내는 타입 파라미터는 `covariant(out 한정자)` 이다.
- `Array, MutableList, MutableSet, MutableMap` 의 타입 파라미터는 `covariant(out 한정자)` 이다.
- return 만 되는 타입에는 `covariant(out 한정자)`를 사용
- 허용만 되는 타입에는 `contravariant(in 한정자)`를 사용