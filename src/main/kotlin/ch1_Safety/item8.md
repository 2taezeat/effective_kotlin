
# 아이템 8. 적절하게 null을 사용하라.

- null은 `값이 부족하다(lack of value)` 는 것을 나타낸다. 
- null은 최대한 명확한 의미를 갖는 것이 좋다. null을 처리하는 것은 API 사용하는 개발자 이기 떄문이다.


#### 기본적으로 nullable 타입은 세 가지 방법으로 처리한다. 
1. `?., 스마트캐스팅, Elvis 연산자`, 안전 호출
2. `Error throw`
3. 함수 또는 프로퍼티를 리팩터링해서 nullable 타입이 나오지 않게 바꾼다.



```kotlin
val printer: Printer? = getPrinter()
printer.print() // 컴파일 오류

printer?.print() // 안전 호출
if (printer != null) printer.print() // 스마트 캐스팅
printer!!.pritn() // not-null 단언
```


### 안전 호출
```kotlin
val printer: Printer? = getPrinter()

val printerName1 = printer?.name ?: "Unnamed" 
val printerName2 = printer?.name ?: return 
val printerName3 = printer?.name ?: throw Error("Unnamed")
```
무언가 없다는 것을 나타낼 때는 null 이 아닌 빈 컬렉션을 사용하는 것이 일반적이다. `Collection<T>?.orEmpty()`


### Error throw
오류를 강제롤 발생시킬 때는 `throw, !!, requireNotNul, checkNotNull` 을 활용한다.
```kotlin
fun process(user: User) {
    requireNotNull(user.name)
    val context = checkNotNull(context)
    val networkService = 
        getNetworkService(context) ?: throw NoInternetConnection()
    networkService.getData { data, userData -> 
        show(data!!, userData!!)
    }
}
```


### not-null assertion (!!)
- !!은 타입이 nullable 이지만, null이 나오지 않는다는 것이 거의 확실한 상황에서 많이 사용된다.
- 하지만 미래에 코드가 어떻게 변화될지는 아무도 모른다.
- 변수를 null로 설정하고, 이후에 !! 연산자를 사용하는 방법은 좋지 않은 방법이다. 대신 `delegates` or `lateinit` 사용


### 의미 없는 nullability 피하기
- nullability는 어떻게든 적절하게 처리해야 하므로, 추가 비용이 발생한다.
- 따라서 필요한 경우가 아니라면, nullability 자체를 피하는 것이 좋다.

#### nullability를 피할 때 사용할 수 있는 방법들
1. 클래스에서 nullability에 다라 여러 함수를 만들어서 제공한다. `get`, `getOrNull`
2. 어떤 값이 클래스 생성 이후에 확실하게 설정된다는 보장이 있다면, `lateinit` 와 `notNull delgeate`를 사용
3. 요소가 부족하다는 것을 나타내렴년, 빈 컬렉션을 사용해라.
4. `nullable enum`과 `None enum`은 완전히 다른 의미이다. `None enum` 정의에 없으므로, 필요한 경우에 사용하는 쪽에서 추가해서 활용할 수 있다.


### lateinit, notNull delegate
- `lateinit`은 프로퍼티를 처음 사용하기 전에 반드시 초기화 될 거라고 예상되는 상황에서 사용( Ex. `Activity,onCrete()` )
- 이러한 상황으로는 lifeCycle을 갖는 클래스 처럼 메서드 호출에 명확한 순서가 있을 경우가 있다.
- JVM 에서는 `Int, Long, Double, Boolean`과 같은 기본 타입과 연결된 타입으로 프로퍼티를 초기해야 한다면, 느리지만, `Delegates.notNull`을 사용한다.

```kotlin
var doctorId: Int by Delegates.notNull()
var fromNotification: Boolean by Delegates.notNull()
```
