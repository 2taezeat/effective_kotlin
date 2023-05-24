
# 아이템 13. Unit? 을 리턴하지 말라.


- Boolean이 true or false 갖는 것 처럼, Unit? 은 Unit or null 이라는 값을 가질 수 있다.
- Unit?으로 Bool을 표현하는 것은 오해의 소지가 있으며, 예측하기 어려운 오류를 만들 수 있다.


```kotlin
if (!keyIsCorrect(key)) return // good

verifyKey(key) ?: return // bad
```

