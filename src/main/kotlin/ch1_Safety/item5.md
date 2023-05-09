# 아이템 5. 예외를 활용해 코드에 제한을 걸어라

- 확실하게 어떤 형태로 동작해야 하는 코드가 있다면, 예외를 활용해 제한을 걸어주는 것이 좋다.
- 제한을 걸면, 예상하지 못한 동작을 하지 않고 throw 한다는 장점
- 코드가 자체적으로 검사됨
- 스마트 캐스트 기능 활용 가능


**코틀린에서 코드의 동작에 제한을 걸때 사용하는 것들**
- require : 아규먼트 제한
- check : 상태와 관련된 동작 제한
- assert : 어떤 것이 true 인지 확인, 테스트 모드에서만 작동
- return 또는 throw와 함께 활용하는 Elvis 연산자


### 아규먼트
require 함수는 조건을 만족하지 못할 때, IllegalArgumentException 을 발생시킨다.

```kotlin
fun sendMail(user: User, message: String) {
    requireNotNull(user.email)
    require(isValidEmail(user,email))
}

```

### 상태
- 어떤 구체적인 조건을 만족할 때만 함수를 사용할 수 있게 해야 하는 경우
- check 함수는 require 와 비슷하짐만, 지정된 예측을 만족하지 못할때 IllegalStateException을 throw 한다.
- 보통 require 블록 다음에 check 블록을 배치한다.

```kotlin
fun getUserInfo(): UserInfo {
    checkNotNull(token)
}

```

### Assert

- Assert 계얼 함수는 코드를 자체 점검한다.
- 실행 시점에 정확하게 어떻게 되는지 확인 할 수 있따.
- 실제 코드가 더 빠른 시점에 실패하게 만듭니다. 따라서 예상하지 못한 동작이 언제 어디서 실행되었는지 쉽게 찾을 수 있다.


### nullability 와 스마트 캐스팅
- require와 check 블록으로 어떤 조건을 확인해서 true가 나왔다면, 해당 조건은 이후로도 true 일 거라고 가정한다.
- 이를 활용해서 타입 비교를 했따면, 스마트 캐스트가 작동
한다.


```kotlin
fun changeDress(person: Person) {
    require(person.outfit is Dress)
    val dress: Dress = person.outfit // outfit 프로퍼티가 Dress로 스마트 캐스트 된다.
}

```

```kotlin
class Person(val email: String?)
fun validataeEmail(email: String) {
    //...
}

fun sendEmail(person: Person, text: String) {
    val email = requireNotNull(person.email)
    validataeEmail(person.email)
}

```

nullability 를 목적으로, 오른쪽에 throw 또는 return을 두고 Elvis 연산자를 활용하는 경우도 가능하다.

```kotlin


fun sendEmail(person: Person, text: String) {
    val email: String = person.email ?: run {
        log("Email not sent, no email address")
        return
    } 
}

```