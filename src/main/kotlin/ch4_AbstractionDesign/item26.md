# 아이템 26. 함수 내부의 추상활 레벨을 통일해라

- 높은 레벨일 수록 단순함을 얻지만, 제어력(control)을 잃습니다.
- 계층이 잘 분리 되면, 해당 계층만 생각하면 되서 좋다.
- 함수도 높은 레벨과 낮은 레벨을 구분해서 사용해야 한다는 원칙 이를 `Single Level of Abstraction, SLA` 원칙이라고 한다.
- 비지니스 로직에 가까울 수록 높은 레벨, 입출력을 나타내는 모듈일 수록 낮은 레벨의 모듈

```kotlin
fun makeCoffee() {
    boilWater()
    brewCoffee()
    pourCoffee()
}

private fun boilWater() {
    
}
private fun brewCoffee() {

}
private fun pourCoffee() {

}

```
