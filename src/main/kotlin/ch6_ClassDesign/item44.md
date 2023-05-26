# 아이템 44. 멤버 확장 함수의 사용을 피하라

- `멤버 확장 함수`의 사용을 피하라

```kotlin
class PhoneBookInCorrect {
    // bad
    fun String.isPhoneNumber() = length == 7 && all { it.isDigit() }
    
}
```