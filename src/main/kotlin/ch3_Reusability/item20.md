# 아이템 20. 일반적인 알고리즘을 반복해서 구현하지 말라

```kotlin
val percent = when {
    numberFromUser > 100 -> 100
    numberFromUser < 0 -> 0
    else ->  numberFromUser
}
// ===
val percent = numberFromUser.coerceIn(0, 100) // stdlib 함수
```