
# 아이템 14. 변수 타입이 명확하지 않은 경우 확실하게 지정하라.


- 가독성과 읽는 사람을 생각해서, 타입을 지정해야 한다.
- 유형이 명확하지 않을 때는 타입을 지정해야 한다.


```kotlin
val data = getSomeData() // bad

val data: UserData = getSomeData() // good
```

