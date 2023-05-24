# 아이템 17. 이름 있는 argument 를 사용하라
 
- 이름을 기반으로 값이 무엇을 나타내는 지 알 수 있다.
- 파라미터 입력 순서와 상관 없으므로 안전하다.
- 코드를 읽는 다른 사람에게 argument 정보를 줘야 한다.

```kotlin

sleep(100) // bad

sleep(timeMillis = 100) // good!
```