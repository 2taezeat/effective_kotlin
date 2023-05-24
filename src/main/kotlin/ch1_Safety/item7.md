
# 아이템 7. 결과 부족이 발생할 경우 null과 Failure를 사용하라

- 예외 thorw는 예외적인 상황이 발생할 때만 사용
- 원하는 결과를 처리하지 못하는 경우에는 `null` 또는 `Failure(sealed 클래스)`를 사용한다.



```kotlin
val age = usetText.readObjectOrNull<Person>().age ?: -1 // 엘비스 연산자


val person = usetText.readObjectOrNull<Person>() // Failure 클래스
val age = when(person) {
    is Success -> person.age
    is Failure -> -1
}

```

```
- 예외는 놓칠 수도 있으며, 전체 애플리케이션을 중지시킬 수도 있습니다. 
- null 값과 sealed result 클래스는 명시적으로 처리해야 하며, 애플리케이션의 흐름을 중지하지도 않습니다.
```



```
- 추가적인 정보를 전달해야 한다면 sealed result 사용, 그렇지 않으면 null을 사용 
- Failure는 처리할 때 필요한 정보를 가질 수 있다.
```


```
일반적으로 두 가지 형태의 함수를 사용한다, 예상 할 수 있을때와 없을때 사용한다.
- get: 특정 위치에 있는 있는 요소를 추출할 때 사용, 만약 없다면 IndexOutOfBoundsException 발생. 
- getOrNull: out of range 오류가 발생할 수 있는 경우에 사용하며, 발생한 경우에는 null을 리턴
```