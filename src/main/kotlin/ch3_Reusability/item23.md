# 아이템 23. 타입 파라미터의 섀도잉을 피하라


- 프로퍼티와 파리미터가 같은 이름을 가질 수 있는데, 이렇게 되면 지역 파라미터가 외부 스코프에 있는 프로퍼티를 가리게 된다. 이를 `Shadowing` 이라고 한다.
- 어떠한 경고도 발생시키지 않기 때문에, 쉽게 찾기 어렵다.


```kotlin
class Forest(val name: String) {
    
    fun addTree(name: String) {
        
    }
}
```


- `클래스 타입 파라미터`와 `함수 타입 파라미터` 사이에서도 `섀도잉`은 발생한다.
```kotlin
interface Tree
class Birch: Tree
class Spruce: Tree

class Forest<T: Tree> {
    fun <T: Tree> addTree(tree: T) {
        
    }
}

val forest = Forest<Birch>
forest.addTree(Birch())
forest.addTree(Spruce())

///////////////////////////////////////////////

class Forest<T: Tree> {
    fun addTree(tree: T) {

    }
}

val forest = Forest<Birch>
forest.addTree(Birch())
forest.addTree(Spruce()) // Compile, Error
```
