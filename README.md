# LPAS
LPAS: Little Pseudo Assembly Language Simulator

## Index


## Intro

 슈도 어셈블리어는 PLP(Programming Language Pragmatics) 교재에서 소개하고 있는
어셈블리어로 특정 기계어에 종속되지 않는 형태로 어셈블리코드를 기술할 수 있게 해주는 언
어이다. 또한 고급 프로그래밍 언어 형태의 지정문이나 if 구문을 사용하여 가독성을 높였다.  


  슈도 어셈블리어는 프로그램 카운터(pc)와 레지스터, 그리고 메모리 형태의 저장장소 관리
기능을 가지며, 일반적인 어셈블리어가 가지는 load/store, ALU의 계산 기능, 비교에 의한
jump 기능 등을 지원한다.  


  여기서 소개되는 LPA는 위에서 나열한 기본적인 어셈블리어의 기능 이외에 배열의 선언,
값지정, 값참조를 위한 주소를 이용한 load/store 기능을 지원한다. 그리고 int와 float 타입
만 지원하며, 이들 데이터 타입의 메모리 크기는 1이라고 가정한다.  


  어셈블리어의 모든 기능을 사용한다면 프로그램의 로직과 동작을 번역하여 표현할 수 있을
것이다. 여기서는 함수 호출이나 추가적인 연산 기능, 타입 지원 등은 제외하고 비교적 간단
한 어셈블리어를 정의하여 사용하고자 한다.  


### Collaborators
- **[Prof.Lee Eun Jeong](mailto:ejlee@kyonggi.ac.kr)** - Department of Computer Science, KYONGGI Univ.  
- **[Lee Jun Seong](https://github.com/krPlatypus)**  - Department of Computer Science, KYONGGI Univ.    


## Example
1. 0부터 num까지 합계 구하기 (for 루프)  
```
    number := 5                 -- 변수 초기화
    r1 := 0 — 루프 인덱스 변수   -- 레지스터 값 설정
    r2 := number                -- 메모리에서 변수값 로드
    r3 := 0 — sum               -- 레지스터 값 설정
    goto L1                     -- 무조건 goto
L2: r3 := r3 + r1               -- 레지스터 연산
    r1 := r1 + 1                -- 레지스터 연산
    if (r1 < r2) goto L2        -- 레지스터 비교 & 조건부 점프
L1: sum := r3                   -- 레지스터 값 변수에 저장
    print “a까지의 sum = ”       -- 문자열
    print r3                    -- 레지스터
```

