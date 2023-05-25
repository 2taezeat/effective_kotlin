# 아이템 30. 요소의 가시성을 최소화하라

- 클래스의 상태를 나타내는 프로퍼티를 외부에서 변경할 수 있다면, 클래스는 자신의 상태를 보장할 수 없다.
- 세터만 private 으로 만드는 코드는 자주 사용된다.
- 상태 변경은 동시성을 처리할 때 중요하다, 상태 변경은 병렬 프로그래밍에서 문제가 된다.
```kotlin
class CounterSet {
    var elementsAdded: Int = 0
        private set
    
    fun add() {
        elementsAdded++
    }
}
```


### 가시성 한정자(visibility modifier)
#### 클래스 멤버
- public: 어디에서나 볼 수 있다. (디폴트)
- private: 클래스 내부에서만 볼 수 있다.
- protected: 클래스와 서브 클래스 내부에서만 볼 수 있다.
- internal: 모듈 내부에서만 볼 수 있다.

#### 톱 레벨
- public: 어디에서나 볼 수 있다. (디폴트)
- private: 같은 파일 내부에섬나 볼 수 있다.
- internal: 모듈 내부에서만 볼 수 있다.


#### 코틀린에서 모듈이란 함께 컴파일 되는 코틀린 소스를 의미한다. 따라서, 다음을 의미한다.
- Gradle 소스 세트
- Maven 프로젝트
- Intellij IDEA 모듈
- Ant 태스크 한 번으로 컴파일 되는 파일 세트

#### data class 에는 가시성 한정자를 적용하지 않는 것이 좋다. (숨길 이유가 없기 때문에)