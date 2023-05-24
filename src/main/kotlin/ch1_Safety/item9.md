
# 아이템 9. use를 사용하여 리소스를 닫아라. 

- 더 이상 필요하지 않을 때, close 메서드를 사용해서 명시적으로 닫아야 하는 리소스 들이 있다.
- Ex.) `InputStream, OutputStream, Socket, IoReader, Connection`
- 이러한 모든 리소스는 최종적으로 리소스에 대한 레퍼런스가 없어질 ㄷ때, 가비지 컬렉터가 처리한다.
- 하지만 느리며, 비용이 많이 들어간다. 따라서 명시적으로 close 메서드를 호출하는 것이 좋다.
- Closeable 객체에서 use 함수를 사용하면 예외 처리 안해도 되서 좋다.


#### 기본적으로 nullable 타입은 세 가지 방법으로 처리한다. 
1. `?., 스마트캐스팅, Elvis 연산자`, 안전 호출
2. `Error throw`
3. 함수 또는 프로퍼티를 리팩터링해서 nullable 타입이 나오지 않게 바꾼다.

```kotlin
fun countCharactersInFile(path: String): INt {
    val reader = BufferedReader(FileReader(path))
    try {
        return reader.lineSequence().sumBy { it.length }
    } finally {
        reader.close()
    }
}
```

```kotlin
fun countCharactersInFile(path: String): INt {
    val reader = BufferedReader(FileReader(path))
    reader.use {
        return reader.lineSequence().sumBy { it.length }
    }
}
```

코틀린 표준 라이브러리는 파일을 한 줄씩 처리할 때, 사용할 수 있는 `useLines` 도 제공한다. 
```kotlin
fun countCharactersInFile(path: String): INt {
    File(path).useLines { lines -> 
        return lines.sumBy { it.length }
    }
}
```