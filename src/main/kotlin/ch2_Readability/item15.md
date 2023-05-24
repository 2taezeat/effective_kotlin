# 아이템 15. receiver 를 명시적으로 참조하라.

- 무언가를 더 자세하게 설명하기 위해, 명시적으로 긴 코드를 사용할 때가 있다.
- 대표적으로 함수와 프로퍼트를 지역 또는 톱레벨 변수가 아닌 다른 리시버로 부터 가져온다는 것을 나타낼 때가 있다.
- 클래스의 메서드라는 것을 나타내기 위해 `this`가 있다.


```kotlin
class Node(val name: String) {
    
    fun makeChild(childName: String) = 
        create("$name.$childName").apply {
            print("Created ${this?.name} in" + " ${this@Node.name}")
        }
    
    fun create(name: String): Node? = Node(name)
    
}

val node = Node("parent")
node.makeChild("child") // => "Created parent.child in parent"
```
- 리시버가 명확하지 않다면, 명시적으로 리시버를 적어서 명확하게 해야 한다.
- 레이블 없이 리시버를 사용하면, 가장 가까운 리시버를 의미한다.

