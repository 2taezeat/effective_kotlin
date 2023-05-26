# 아이템 47, 인라인 클래스의 사용을 고려하라.

- inline 은 다른 자료형을 래핑해서 새로운 자로형을 만들 때 많이 사용
- 생성자 프로퍼티가 하나인 클래스에 사용, 해당 객체를 사용하는 위치가 모두 해당 프로퍼티로 교체
- 클래스의 메서드는 모두 정적 메서드로 만들어진다.
```kotlin
inline class Name(private val value: String) {
    
}

val name: Name = Name("Marcin")
// ===
val name: String = "Marcin" // 컴파일 때 이렇게 변경
```

#### inline 클래스가 자주 사용되는 경우
1. 측정 단위를 표현할 때 (타입 안정성)
```kotlin
inline class Minutes(val minutes: Int) {
    fun toMillis(): Millis = Millis(minutes * 60 * 1000)
}

inline class Millis(val millisseconds: Int) {
    
}

interface Timer {
    fun callAfter(timeMillis: Millis, callback: ()->Unit )
}

```

2. 타입 오용으로 발생하는 문제를 막을 때 (타입 안정성)

