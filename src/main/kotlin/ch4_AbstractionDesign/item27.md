# 아이템 27. 변화로부터 코드를 보호하려면 추상화를 사용하라

1. 상수
   1. 이름을 붙일 수 있다.
   2. 나중에 해당 값을 쉽게 변경할 수 있다.
```kotlin
const val MIN_PASSWORD_LENGTH = 7
fun isPasswordValid(text: String) {
     if (text.length < MIN_PASSWORD_LENGTH) return false
}
```

2. 함수
   1. 확장 함수
   2. 함수는 추상화를 표현하는 수단이다.
   3. 함수 시그니처는 이 함수가 어떤 추상화를 표현하고 있는지 알려주기 때문에 이름은 굉장히 중요하다.

```kotlin
fun Context.showMessage(message: String, duration: MessageLength = messageLength.LONG) {
    val toastDuration = when (duration) {
        SHORT -> Length.LENGTH_SHORT
        LONG -> Length.LENGHTH_LONG
     }
    toast.makeText(this, message, toastDuration).show()
}

enum class MessageLength { SHORT, LONG }
```

3. 클래스
   1. 클래스가 함수보다 더 강력한 이유는 상태를 가질 수 있으며, 많은 함수를 가질 수 있다는 점 때문이다.
```kotlin
class MessageDisplay(val context: Context) {
   fun show(message: String, duration: MessageLength = messageLength.LONG) {
      val toastDuration = when (duration) {
         SHORT -> Length.LENGTH_SHORT
         LONG -> Length.LENGHTH_LONG
      }
      toast.makeText(this, message, toastDuration).show()
   }    
}


enum class MessageLength { SHORT, LONG }

val messageDisplay = MessageDisplay(context)
messageDisplay.show("myMessage")

val messageDisplay: MessageDisplay = mockk() // test
```



4. 인터페이스
   1. 클래스를 인터페이스 뒤에 숨긴다.
   2. coupling을 줄인다.
   3. 인터페이스 faking이 class mocking 보다 간단흐므로, 별도의 모킹 라이브러리를 사용하지 않아도 된다.

```kotlin
interface MessageDisplay {
   fun show(message: String, duration: MessageLength = LONG)
}

class ToastDisplay(val context: Context) : MessageDisplay {
   override fun show(message: String, duration: MessageLength) {
      val toastDuration = when (duration) {
         SHORT -> Length.LENGTH_SHORT
         LONG -> Length.LENGHTH_LONG
      }
      toast.makeText(this, message, toastDuration).show()
   }    
}


enum class MessageLength { SHORT, LONG }

val messageDisplay: MessageDisplay = TestMessageDisplay() // test
```

5. Id 만들기 (객체 래핑)
```kotlin
var nextId: Int = 0 // bad

val nexId = nextId++
```
- 이 코드의 ID는 무조건 0부터 시작합니다.
- 이 코드는 thread-safe 하지 않습니다.
```kotlin
private var nextId: Int = 0 // better
fun getNextId(): Int = nextId++

val nexId = getNextId()
```

```kotlin
data class Id(private val id: Int) // best

private var nextId: Int = 0
fun getNextId(): Int = Id(nextId++)
```


### 추상화의 문제
- 너무 많은 것을 숨기면 결과를 이해하는 것 자체가 어려워진다.