# 아이템 42. compareTo의 규약을 지켜라

- 일반적으로 어떤 프로퍼티 하나를 기반으로 순서를 지정하는 것이 충분하기에 compareTo를 구현할 일은 별로 없다.
```kotlin
class User(val name: String, val surname: String)
val names = listOf<User>( /**/ )

val sorted_1 = names.sortedBy { it.name }

val sorted_2 = names.sortedWith( compareBy({ it.name }, { it.surname }) )
```
