# 아이템 46. 함수 타입 파라미터를 갖는 함수에 inline 한정자를 붙여라


- `함수를 호출하는 부분`을 함수의 `본문`으로 대체하면 `점프`가 일어나지 않는다. (`inline` 역할)


#### inline 한정자 장점
- 타입 아규먼트에 `reified` 한정자를 붙여서 사용할 수 있다.
```kotlin
if (any is List<Int>) { }// 불가능 한 코드
    
inline fun <reified T> printTypeName() {
    print(T::class.simpleName)
}

printTypeName<Int>() // Int
printTypeName<String>() // String

```

- `함수 타입` `파라미터`를 가진 함수가 휠씬 빠르게 동작
- `non-local` `return`을 사용할 수 있다.