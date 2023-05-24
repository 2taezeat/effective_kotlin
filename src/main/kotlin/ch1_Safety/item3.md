# 아이템 3. 최대한 플랫폼 타입을 사용하지 말라

- 다른 프로그래밍 언어에서 와서 nullable 여부를 알 수 없는 타입을 `플랫폼 타입`이라 한다.
- 이는 NPE 발생 위험이 있으므로, 최대한 해당 코드를 제거 하거나, 어노테이션을 활용해서 처리 해야 한다.


```java
// java code
public class JavaClass {
    public String getValue() {
        return null;
    }
}
```

```kotlin
fun statedType() {
    val value: String = JavaClass().value // 값을 가져오는 위치에서 NPE 발생, 찾기 쉬움.
    //...
    println(value.length)
}


fun platformType() {
    val value = JavaClass().value
    //...
    println(value.length) // 값을 활용할떄, NPE 발생, 찾기 어려움.
}

```


