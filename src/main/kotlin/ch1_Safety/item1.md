# 아이템 1. 가변성을 제한하라

**가변성 제한의 이유**

- 디버깅 어려움.
- 가변성이 있으면, 코드의 실행을 추론하기 어려움
- 멀티스레드 프로그램일 때는 적절한 동기화가 필요
- 테스트하기 어려움
- 사태 변경이 일어날 때, 전파해주어야 함.

## 코틀린에서 가변성 제한하기
- 읽기 전용 프로퍼티(val)
- 가변 컬렉션과 읽기 전용 컬렉션 구분하기
- 데이터 클래스의 copy


### 읽기 전용 프로퍼티
- 프로퍼티를 읽을 수만 있다는 속성(읽기 전용)과 값이 변할 수 없는 것(가변성) 은 다른 이야기
- val -> getter만 제공, var -> getter, setter 둘 다 제공, 따라서, val을 var로 override 할 수 있음.
- 읽기 전용 프로퍼티 val의 값은 변경될 수 있기는 하지만, 프로퍼티 레퍼런스 자체를 변경할 수는 없으므로 동기화 문제 등을 줄일 수 있음.

```kotlin
val name = "asd"
val surname = "qwe"

val fullName: String?
    get() = name?.let { "$it $surname"}

val fullName2: String? = name?.let { "$it $surname" }

fullName은 getter를 사용하므로, 값을 사용하는 시점의 name에 따라서 다른 결과가 나올 수 있기 때문에, Smart Cast 불가.
```

### 가변 컬렉션과 읽기 전용 컬렉션 구분하기

- mutable이 붙은 인터페이스는 대응되는 읽기 전용 인터페이스를 상속 받아서, 변경을 위한 메서드를 추가한 것.
- JVM에서 listOf는 자바의 List 인터페이스를 구현한 Array.ArrayList(add 연산 구현 안됨) 인스턴스를 리턴
- 따라서 코틀린에서 읽기 전용 컬렉션 mutable 컬렉션으로 다운캐스팅하면 안됨.
- 읽기 전용에서 mutable로 변경 해야 한다면, copy를 해서 새로운 mutagble 컬렉션을 만들어야 함. 



```kotlin
val list = listOf(1,2,3)
if (list is MutableList) { // 이렇게 하면 안됨
    list.add(4)
}

val mutableList = list.toMutableList() // 이렇게 해야 한다.
mutableList.add(4)

```


### 데이터 클래스의 copy
- mutable 객체는 예측하기 어려우며 위험
- immutable 객체는 변경할 수 없다는 단점, 따라서 자신의 일부를 수정한 새로운 객체를 만들어 내는 메서드를 가져야 함. (ex. plus, map)
- data 한정자는 copy 메서드로 위 상황을 해결. 


### 다른 종류의 변경 가능 지점

```kotlin
val list1 = mutableListOf<Int>()
var list2 = listOf<Int>()

list1.add(1) // 멀티 스레드 처리가 이루어 질 경우 동기화 문제가 발생할 위험.
list2 = list2 + 1 // 프로퍼티 자체가 변경 가능 지점, 멀티 스레드 안정성이 더 좋다고 할 수 있음.

```
















