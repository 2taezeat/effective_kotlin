# 아이템 18. 코딩 컨벤션을 지켜라

- 코틀린 컨벤션 중, 자주 위반 되는 규칙 중 하나는 클래스와 함수에서 많은 파라미터들을 가지면, 한 줄씩 작성해야 한다.

```kotlin

class Person(val id: Int, val name: String, val surname: String ) { // bad
    
}

class Person(
    val id: Int,
    val name: String,
    val surname: String ) { // good
    
    
}
```