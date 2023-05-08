# 아이템 2. 변수의 스코프를 최소화 하라.

- 프로퍼티 보다는 지역 변수를 사용하는 것이 좋다.
- 최대한 좁은 스코프를 갖게 변수를 사용. EX.) 반복문
- 프로그램 추적과 관리를 위함.
- 여러 프로퍼티를 한꺼번에 설정해야 하는 경우 구조분해 선언을 활용해라.
- 람다에서 변수를 캡쳐함으로 스코프 최소화를 해야 한다.

```kotlin
// bad case
var user: User
for (i in users.indices) {
    user = users[i]
    print("$user")
}

// good case
for ((i, user) in users.withIndex() {
    print("$user")
})

```


