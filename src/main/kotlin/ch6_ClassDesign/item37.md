# 아이템 37. 데이터 집합 표현에 data 한정자를 사용하라

#### data 한정자를 붙이면, 자동으로 생성되는 함수
- toString
- equals
- hashCode
- copy
- componentN


### 튜플 < 데이터 클래스
- `return` 타입 명확
- 가독성 향상
- 컴파일에 경고함으로 안전

```kotlin
fun String.parseName(): Pair<String, String>? { // bad
    
}


data class FullName(
    val firstName: String,
    val lastName: String
)


fun String.parseName(): FullName? { // good
    
}

```