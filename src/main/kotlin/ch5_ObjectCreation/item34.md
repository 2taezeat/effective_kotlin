# 아이템 34. 기본 생성자에 이름 있는 옵션 아규먼트를 사용하라


### 점층적 생성자 패턴 < Default Argument(기본 생성자)

```kotlin
class Pizza(
    val size: String,
    val cheese: Int = 0,
    val olives: Int = 0,
    val bacon: Int = 0,
)

val myPizza = Pizza("L", olives = 3)
```


### 빌더 패턴 < Default Argument or DSL

- 코틀린에서 빌더 패턴은 자주 사용되지 않는다. 
- 빌더 패턴을 사용하는 다른 언어로 작성된 라이브러리를 그대로 옮길 때 만 사용
- 디폴트 아규먼트와 DSL 을 지원하지 않는 다른 언어에서 쉽게 사여용할 수 있게 API를 설계 할 때 만 사용