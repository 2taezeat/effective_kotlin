# 아이템 36. 상속보다는 컴포지션(합성)을 사용하라.


#### 상속의 단점
- 상속은 하나의 클래스만을 대상으로 할 수 있다.
- 거대한 BaseXX 클래스가 만들어지고, 깊고 복잡한 계층구조가 만들어진다.
- 상속은 클래스의 모든 것을 가져 오게 된다. ISP
- 상속은 이해하기 어렵다. 슈퍼클래스를 매번 확인해야 한다.

#### 따라서 상속보다는 합성을 사용해라.
- 합성을 하면, 하나의 클래스 내부에서 여러 기능을 재사용할 수 있다. 
- 반면, 상속은 하나 이상의 클래스를 상속할 수 없다.
- 합성은 원하는 행위만 가져 올 수 있다.

```kotlin
class Progress {
    fun showProgress() {
        
    }
    fun hideProgress() {
        
    }
}

class ProfileLoader {
    val progress = Progress()
    val finishedAlert = FinishedAlert()
    
    
    fun load() {
        progress.showProgress()
        // 프로파일을 읽어 드림
        progress.hideProgress()
        finishedAlert.show() // 추가 기능
    }
}


class ImageLoader {
    val progress = Progress()

    fun load() {
        progress.showProgress()
        // 이미지 를 읽어 들임
        progress.hideProgress()
    }
}

```

#### 다형성이 필요할 때는 위임 패턴을 사용해라.
- 위임 패턴은 클래스가 인터페이스를 상속받게 하고, 포함한 객체의 메서들을 활용해서, 인터페이스에서 정의한 메서드를 구현하는 패턴
- 이렇게 구현된 메서드르 `포워딩 메서드(forwarding method)` 라고 부른다.
- 코틀린에서는 `포워딩 메서드(forwarding method)` 이 자동으로 컴파일 시점에 만들어진다.

```kotlin
class CounterSet<T>(
    private val innerSet: MutableSet<T> = mutableSetOf()
) : MutableSet<T> by innerSet {
    
    var elementsAdded: Int = 0
        private set

    override fun add(element: T): Boolean{
        elementsAdded++
        return innerSet.add(element)
    }
    
    override fun addAll(elements: Collection<T>): Boolean{
        elementsAdded += elements.size
        return innerSet.addAll(elements)
    }
}
```

#### 상속은 명확한 `is-a` 관계일 때만 사용해야 한다.
- 슈퍼클래스의 모든 단위 테스트는 서브클래스로도 통과할 수 있어야 한다.
- Ex.) `Activity`, `View`