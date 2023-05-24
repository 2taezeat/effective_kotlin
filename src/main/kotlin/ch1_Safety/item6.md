# 아이템 6. 사용자 정의 오류보다는 표준 오류를 사용하라 

- IllegalArgumentException, IllegalStateException: require와 check를 통해 확인
- IndexOutOfBoundsException: 인덱스 파라미터의 값이 범위를 벗어났다.
- ConCurrentModificationException: 동시 수정 금지했는데, 발생할 때 나타남.
- UnsupportedOperationException: 사용자가 사용하려고 했던 메서드가 현재 객체에서는 사용할 수 없다는 것
- NoSuchElementException: 사용자가 사용하려고 했떤 요소가 존재하지 않음을 나타냄.



