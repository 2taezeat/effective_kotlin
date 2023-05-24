# 아이템 16. 프로퍼티는 동작이 아니라 상태를 나타내야 한다.

- 코틀린의 Property 는 자바의 Field와 비슷해 보이지만, 완전 다른 개념이다.
```kotlin
val name: String? = null // kotlin property

String name = null? // java field
```
- 공통점은 둘 다 데이터를 저장한다는 점, 프로퍼티에는 더 많은 기능이 있다.
- 프로퍼티는 사용자 정의 getter, setter를 가질 수 있따. 
- `field 식별자`는 프로퍼티의 데이터를 저장하는 `backing field`에 대한 레퍼런스이다. (디폴트로 생성)
```kotlin
var name: String? = null
    get() = field?.uppercase()
    set(value) {
        if(!value.isNullOrBlank())
            field = value
    }

```


- 모든 property 는 디폴트로 캡슐화 되어 있다.

```kotlin
var date: Date
    get() = Date(millis)
    set(value) {
        millis = value.time
    }
```

- property는 field가 필요 없다. 오히려 property는 개념적으로 접근자를 나타냅니다.
- 따라서 코틀린은 인터페이스에도 property를 정의할 수 있다.
- property를 위임할 수 도 있다. 
```kotlin
interface Person {
    val name: String
}

open class Supercomputer {
    open val theAnswer: Long = 42
}

class AppleComputer: Supercomputer {
    override val theAnswer: Long = 1_800_275_2273
}

val db: Database by lazy { connectToDb() }
```

- property 는 본질적으로 함수이므로, 확장 property 도 만들 수 있다.

```kotlin
val Context.preferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)
```

- 원칙적으로 property 는 상태를 나타내거나 설저하기 위한 목적으로만 사용해야 하고, 다른 로직 등은 포함하지 않아야 한다.

#### property 대신 함수를 사용하는 것이 좋은 경우
1. 연산 비용이 높거나 복잡도가 O(1) 보다 큰 경우
2. 비지니스 로직을 포함하는 경우
3. 결정적이지 않은 경우
4. 변환의 경우
5. getter에서 property 상태 변경이 일어나야 하는 경우


- 반대로 상태를 추출/설정할 떄는 프로퍼티를 사용해야 한다.

```kotlin
class User { // bad
    private var name: String = ""
    
    fun getName() = name
    
    fun setName(name: String) {
        this.name = name
    }
}

class User { // good!
    var name: String = ""
}
```