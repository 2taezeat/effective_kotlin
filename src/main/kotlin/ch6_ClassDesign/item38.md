# 아이템 38. 연산 또는 액션을 전달할 때는 인터페이스 대신 함수 타입을 사용하라

- `SAM(Single-Abstract-Method)`: 메서드가 하나만 있는 인터페이스
- 함수가 `SAM`을 받는 다면, 이러한 인터페이스를 구현한 객체를 전달 받는다는 의미이다.

```kotlin
fun setOnClickListener(listener: OnClick) {
    
}

setOnClickListener(object: OnClick {
    override fun clicked(view: View) {
        
    }
})
```



- SAM 대신 `함수 타입`을 사용하면 더 좋다.
```kotlin
fun setOnClickListener(listener: (View) -> Unit) {
    
}

setOnClickListener { fun(view) { } } // 람다 표현식 또는 익명 함수로 전달

setOnClickListener {::println} // 함수 레퍼런스로 전달
setOnClickListener {this::showUsers} // 함수 레퍼런스로 전달

class ClickListener: (View)-> Unit {
    override fun invoke(view:View) {
        
    }
}
setOnClickListener { ClickListener() }  // 선언된 함수 타입을 구현한 객체로 전달
```
- 함수 타입도 이름을 붙일 수 있다.
```kotlin
typealias OnClick = (View) -> Unit

fun setOnClickListener(listener: OnClick) {
    
}

typealias OnClick = (view: View) -> Unit

```
- `SAM`와 달리 `함수 타입`은, 독립적으로 변경할 수 있다는 장점이 있다. 

```kotlin
class CalendarView { // SAM
    var listener: Listener? = null
    
    interface Listener {
        fun onDateClicked(date: Date)
        fun onPageChanged(date: Date)
    }
}

class CalendarView { // 함수 타입
    var onDateClicked: ((date: Date) -> Unit)? = null
    var onPageChanged: ((date: Date) -> Unit)? = null
}
```

- SAM은 코틀린이 아닌 다른 언어에서 사용할 클래스를 설계할 때 사용한다.