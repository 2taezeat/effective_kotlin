# 아이템 45. 불필요한 객체 생성을 피하라

#### 객체를 wrap 하면 비용이 발생한다.

- 64비트 JDK 에서 객체는 8 byte의 배수 만큼 공간을 차지한다.
- 레퍼런스는 8byte 이다.

```kotlin
sealed class LinkedList<T>

class Node<T>(val head: T, val tail: LinkedList<T>) : LinkedList<T>
class Empty<T> : LinkedList<T>()

val list_1: LinkedList<Int> = Node(1, Node(2, Node(3, Empty()))) // 사용


object Empty: LinkedList<Nothing>
val list_2: LinkedList<Int> = Node(1, Node(2, Node(3, Empty))) // 사용

```


#### 무거운 객체를 외부 스코프로 보내기

```kotlin
fun String.isValid(): Boolean {
    return this.matches("[0-9]".toRegex()) // bad
}
print("123".isValid())

private val IS_VALID_REGEX = "[0-9]".toRegex()
fun String.isValid(): Boolean = mathes(IS_VALID_REGEX) // good
```