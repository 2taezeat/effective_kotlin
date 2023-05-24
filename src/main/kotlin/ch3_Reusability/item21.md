# 아이템 21. 일반적인 프로퍼티 패턴은 프로퍼티 위임으로 만들어라

- 프로퍼티 위임 매커닞므을 활용하면, 다양한 패턴들을 만들 수 있다. 
- 코틀린은 프로퍼티 위임을 사용해서 간단하고 type-safe 하게 구현할 수 있다.

```kotlin
val value by lazy { createValue() }

val items: List<Item> by Delegates.observable(listOf()) { _, _, _ ->
    notifyDataSetChanged()
}

val vm: MainViewModel by viewModel()
```

- 프로퍼티 위임은 다른 객체의 메서드를 활용해서 프로퍼티의 접근자(getter, setter)를 만드는 방식이다.
```kotlin
import kotlin.reflect.KProperty

var token: String? = null
    get() {
        println("token returned value $field")
        return field
    }
    set(value) {
        println("token changed from $field to $value")
        field = value
    }
///////////////// 

private class LoggingProperty<T>(var value: T) {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        println("${prop.name} returned value $value")
    }
    
    operator fun setValue(thisRef: Any?, prop: KProperty<*>, newValue: T){
        val name = prop.name
        println("$name changed from $value to $newValue")
        value = newValue
    }
}

var token: String? by LoggingProperty(null)


```