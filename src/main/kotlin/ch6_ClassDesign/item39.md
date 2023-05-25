# 아이템 39. 태그 클래스보다는 클래스 계층을 사용하라


- 기존 tagged class 는, boilerplate 가 추가 된다.
- 기존 tagged class 는, 일관성과 정확성을 지키기 어렵다.

- tagged class < `sealed class`
- 책임이 분산된다.
- 각각의 객체들은 자신에게 필요한 데이터만 있으며, 적절한 파라미터만 갖습니다.
- `sealed` 한정자는 외부 파일에서 서브클래스를 만드는 행위 자체를 모두 제한한다. 타입이 추가되지 않을 거라는 게 보장된다.
- `when` 구문에서 `else` 구문을 만들지 않아도 된다.

```kotlin
sealed class ValueMatcher<T> {
    abstract fun match(value: T): Boolean
    
    class Equal<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value == this.value
    }

    class NotEqual<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value != this.value
    }

    class EmptyList<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value is List<*> && value.isEmpty()
    }

    class NotEmptyList<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value is List<*> && value.isNotEmpty()
    }
}
```

### 타입 계층과 상태 패턴(state pattern)은 협력 관계 이다.

```kotlin

 import kotlin.properties.Delegates
 sealed class WorkoutState

class PrepareState(val exercise: Exercise) : WorkoutState()
class ExerciseState(val exercise: Exercise) : WorkoutState()
object DoneState : WorkoutState

fun List<Exercise>.toStates(): List<WorkoutState> = 
    flatMap { exercise ->
        listOf(PrepareState(exercise), ExerciseState(exercise))
    } + DoneState

class WorkoutPresenter() {
    private var state: WorkoutState = states.first()
}

private var state: WorkoutState by Delegates.observable(states.first()) { _, _, _ ->
    updateView()
}
```

- 상태는 더 많은 책임을 가진 클래스이다.
- 상태는 변경할 수 있다.

