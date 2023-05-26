# 아이템 49. 하나 이상의 처리 단계를 가진 경우에는 시퀀스를 사용하라

- `Sequence` 는 `Collection`과 달리, `지연(lazy)` 처리 된다.
- `Sequence` 처리 함수들을 사용하면, `데코레이터 패턴`으로 꾸며진 새로운 `Sequence`가 `return` 된다.
- 최종적인 계산은 최종 연산에서 이루어진다.

### `Sequence`의 장점
1. 자연스러운 처리 순서 유지
2. 최소한만 연산
3. 무한 `Sequence` 형태로 사용 가능
4. 각각의 단계에서 `Collection`을 만들어 내지 않습니다.


#### 순서의 중요성
- `Sequence`: `lazy-order` or `element-by-element order` (고전적인 반복문, 조건문과 동일)
- `Iterable`: `eager-order` or `step-by-step order`


#### 최소 연산
```kotlin
// find : 탐색을 앞에서부터 시작하여 만족하는 원소가 있다면 반환, 없다면 null

(1..10).asSequence()
    .filter { print("F$it, "); it % 2 == 1 }
    .map { print("M$it, "); it * 2 }
    .find { it > 5 }
// F1, M1, F2, F3, M3,


(1..10)
    .filter { print("F$it, "); it % 2 == 1 }
    .map { print("M$it, "); it * 2 }
    .find { it > 5 }
// F1, F2, F3, F4, F5, F6, F7, F8, F9, F10,
// M1, M3, M5, M7, M9


```

- `Sequence`가 `Collection`보다 느린 경우는 `sorted` 뿐 이다.
- `sorted`는 `Sequence`를 `List`로 변환한 뒤에 `java stdlib`의 `sort`를 사용해 처리 한다.