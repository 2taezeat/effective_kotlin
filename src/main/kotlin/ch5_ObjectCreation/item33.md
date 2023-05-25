# 아이템 33. 생성자 대신 팩토리 함수를 사용하라

- 생성 패턴은 객체를 생섯ㅅ자로 직접 생성 하지 않고, 별도의 함수를 통해 생성한다.
- 생성자의 역할르 대신 해 주는 함수를 팩토리 함수라고 한다.
- 팩토리 함수는 `secondary constructor` 와 경쟁 관계이다.


## 팩토리 함수의 종류

### Companion 객체 팩토리 함수
- 자바의 정적 팩토리 함수와 같다.
- 가장 일반적인 방법
- `from, of, valueOf, getInstance, newInstance, getType` 이 관용적으로 함수 이름으로 있다.
```kotlin
class MyLinkedList<T>(val head:T, val tail: MyLinkedList<T>) {
    companion object {
        fun <T> of(vararg elements: T): MyLinkedList<T>? {
            
        }
    }
    
}

// 사용
val list = MyLinkedList.of(1,2)

interface MyList<T> {
    companion object {
        fun <T> of(vararg elements: T): MyList<T>? {

        }
    }
}
// 사용
val list = MyList.of(1,2)
```


### 학장 팩토리 함수
- 이미 companion 객체가 존재할 때, 이 객체의 함수처럼 사용할 수 있는 팩토리 함수를 만들어야 할 때 사용
```kotlin
interface Tool {
    companion object {
        //...    
    }
}

fun Tool.Companion.createBigTool(): BigTool {
    //...
}

Tool.createBigTool() // 사용
```




### 톱레벨 팩토리 함수
- 대표적인 예로는 `listOf, setOf, mapOf` 가 있다. 
- `listOf(1,2,3)`이 `List.of(1,2,3)` 보다 휠씬 일기 쉽다.


### 가짜 생성자
- 인터페이스를 위한 샛ㅇ성자를 만들고 싶을 때 사용
- reified 타입 아규먼트를 갖게 하고 싶을 때 사용 
- 코틀린의 생성자는 톱레벨 함수처럼 참조될 수 있다.
```kotlin
class A
val a = A()
val reference: ()->A = ::A

List(4) {"User$it"} // [User0, User1, User2, User3]

```

### 팩토리 클래스의 메서드 
```kotlin
data class Student(
    val id: Int,
    val name: String,
    val surname: String
)

class StudentsFactory {
    var nextId = 0
    fun next(name: String, surname: String) = Student(nextId++, name, surname)
}

val factory = StudentsFactory()
val s1 = factory.next("Marchin", "Moskala")
val s2 = factory.next("Igor", "Wojda")


```



