# 아이템 50. 컬렉션 처리 단계 수를 제한하라

- 모든 `Collection` 처리는 추가적인 `Collection`을 만들어 낸다.
- `Sequence`도 `Sequence` 전체를 wrap 하는 객체가 만들어진다.
- 따라서, 두 처리 모두 처리 요소가 많다면, 꽤 큰 비용이 든다.


```kotlin

fun getNames(): List<String> this // bad
    .map{ it.name }
    .filter { it != null }
    .map { it!! }

fun getNames(): List<String> this // better
    .map{ it.name }
    .filterNotNull()


fun getNames(): List<String> this // best
    .mapNotNull { it.name }
```