# LPAS
LPAS: Little Pseudo Assembly Language Simulator

## Index


## Intro  
  
&nbsp;&nbsp;**슈도 어셈블리어**는 PLP(Programming Language Pragmatics) 교재에서 소개하고 있는  
어셈블리어로 **특정 기계어에 종속되지 않는 형태로 어셈블리코드를 기술할 수 있게 해주는 언어**이다. 
또한 고급 프로그래밍 언어 형태의 지정문이나 if 구문을 사용하여 가독성을 높였다.  


&nbsp;&nbsp;슈도 어셈블리어는 프로그램 카운터(pc)와 레지스터, 그리고 메모리 형태의 저장장소 관리  
기능을 가지며, 일반적인 어셈블리어가 가지는 load/store, ALU의 계산 기능, 비교에 의한 jump 기능  
등을 지원한다.  


&nbsp;&nbsp;여기서 소개되는 LPA는 위에서 나열한 기본적인 어셈블리어의 기능 이외에 배열의 선언,  
값지정, 값참조를 위한 주소를 이용한 load/store 기능을 지원한다. 그리고 int와 float 타입  
만 지원하며, 이들 데이터 타입의 메모리 크기는 1이라고 가정한다.  


&nbsp;&nbsp;어셈블리어의 모든 기능을 사용한다면 프로그램의 로직과 동작을 번역하여 표현할 수 있을  
것이다. 여기서는 함수 호출이나 추가적인 연산 기능, 타입 지원 등은 제외하고 비교적 간단한  
어셈블리어를 정의하여 사용하고자 한다.  


### Collaborators
- **[Prof.Lee Eun Jeong](mailto:ejlee@kyonggi.ac.kr)** - Department of Computer Science, KYONGGI Univ.  
- **[Lee Jun Seong](https://github.com/krPlatypus)**  - Department of Computer Science, KYONGGI Univ.    


## LPA 예제 

(1)  0부터 num까지 합계 구하기 (for 루프)  
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

(2)  0부터 10까지 짝수만 출력하기 (for 루프)  
```
    bound := 10                 -- 변수 초기화
    r1 := 0 — 루프 인덱스 변수   -- 레지스터 값 설정
    r2 := bound                 -- 메모리에서 변수값 로드
    goto L1                     -- 무조건 goto
L2: r3 := r1 % 2                -- 레지스터 연산
    if (r3 != 0) goto L3        -- 레지스터 비교 & 조건부 점프
    print r1                    -- 레지스터
L3: r1 := r1 + 1                -- 레지스터 연산
L1: if (r1 < r2) goto L2        -- 레지스터 비교 & 조건부 점프
```

(3)  입력된 수 num이 3의 배수인지 출력하기 (if-else)  
```
    input r1                    -- 레지스터에 값 입력
    r2 := 3 — 루프 인덱스 변수   -- 레지스터 값 설정
    r3 := r1 % r2               -- 입력값 % 3
    if (r3 == 0) goto L1        -- 3의 배수면 L1으로 점프
    print “3의 배수가 아님”
    goto L2                     -- if 문 다음으로 점프
L1: print “3의 배수임”
L2: num := r1                   -- 변수 num에 입력값 저장
```
- 각 줄 제일 앞에는 탭이 오거나 “라벨:” 다음에 탭이 있어야 함
- -- 라인 코멘트
- 빈줄이나 여분의 whitespace 지원 가능

  
## Little Pseudo Assembly Language 작성 요령
### (1) LPA 언어 소개
- 어휘의 구성  
&ensp;&ensp;o 레지스터  
&ensp;&ensp;&ensp;&ensp;-&ensp;r1, r2와 같은 정수 레지스터와 f1, f2와 같은 실수 레지스터가 있다  
&ensp;&ensp;&ensp;&ensp;-&ensp;정수 레지스터는 `1~8번`, `r1~r8`까지 사용할 수 있음 (다른 번호는 오류 발생)  
&ensp;&ensp;&ensp;&ensp;-&ensp;실수 레지스터는 `1~4번`, `f1~f4`까지 사용할 수 있음 (다른 번호는 오류 발생)  
&ensp;&ensp;o 변수이름  
&ensp;&ensp;&ensp;&ensp;-&ensp;이름에 해당하는 토큰 (키워드, 레지스터 제외)  
&ensp;&ensp;o 라벨  
&ensp;&ensp;&ensp;&ensp;-&ensp;`L1`, `L2`와 같이 L로 시작하고 한자리 숫자로 표현됨 (L1~L9)  
&ensp;&ensp;o 리터럴  
&ensp;&ensp;&ensp;&ensp;-&ensp;정수, 소수, 또는 쌍따옴표로 묶인 문자열  
&ensp;&ensp;&ensp;&ensp;-&ensp;값의 제약 없음  
&ensp;&ensp;o 키워드  
&ensp;&ensp;&ensp;&ensp;-&ensp;`program`, `end` (프로그램의 시작과 끝에 반드시 나와야 하는 키워드임)  
&ensp;&ensp;&ensp;&ensp;-&ensp;`if`, `goto`, `input`, `print`  
&ensp;&ensp;o 기호  
&ensp;&ensp;&ensp;&ensp;-&ensp;`:=`, 수치연산 기호(`+`, `-`, `*`, `/`, `%`), 비교연산 기호(`<`, `<=`, `==`, `!=`, `>`, `>=`)  
&ensp;&ensp;&ensp;&ensp;-&ensp;괄호(조건식 둘러싸는 곳)  
&ensp;&ensp;&ensp;&ensp;-&ensp;: (라벨 바로 뒤에)  
&ensp;&ensp;&ensp;&ensp;-&ensp;& (주소연산), `*`(간접접근연산)  
  
  
- 구문 구조  
&ensp;&ensp;o 기본 구조  
&ensp;&ensp;&ensp;&ensp;-&ensp;`program`  
&ensp;&ensp;&ensp;&ensp;-&ensp;`...`  
&ensp;&ensp;&ensp;&ensp;-&ensp;`end`  
&ensp;&ensp;&ensp;&ensp;-&ensp;문장의 연속 : 문장의 종류는 초기화, 지정, `goto`, `if..goto`, `print`, `input`  
&ensp;&ensp;o 변수 초기화부분  
&ensp;&ensp;&ensp;&ensp;-&ensp;변수 초기화 : `변수 이름 := 값`  
&ensp;&ensp;&ensp;&ensp;-&ensp;변수 이름에 해당하는 메모리가 할당되고 그 값으로 초기화도니다  
&ensp;&ensp;&ensp;&ensp;-&ensp;메모리 할당은 앞에서부터 차례로 할당되며 주소는 1씩 증가한다  
&ensp;&ensp;&ensp;&ensp;-&ensp;값이 정수면 정수 메모리, 실수면 실수 메모리가 할당된다  
&ensp;&ensp;o 배열 선언 : 타입과 이름, 크기로 선언  
&ensp;&ensp;&ensp;&ensp;-&ensp;해당 타입으로 크기만큼 배열이 메모리에 할당된다  
&ensp;&ensp;&ensp;&ensp;-&ensp;배열은 주소+오프셋으로 간접접근 가능한다  
&ensp;&ensp;&ensp;&ensp;-&ensp;값은 0으로 할당된다  
&ensp;&ensp;o 계산부  
&ensp;&ensp;&ensp;&ensp;-&ensp;지정문을 이용하여 값을 지정, 또는 계산할 수 있다  
&ensp;&ensp;&ensp;&ensp;-&ensp;계산은 사칙연산과 % 연산을 이용할 수 있으며, 레지스터나 값으로 계산하여 레지스터에 저장만 가능하다  
&ensp;&ensp;&ensp;&ensp;-&ensp;모든 계산이나 지정은 동일한 타입이어야 한다  
&ensp;&ensp;o 타입 변환  
&ensp;&ensp;&ensp;&ensp;-&ensp;다른 타입 간의 계산이 필요한 경우 명시적으로 타입을 변환해 주어야 한다  
&ensp;&ensp;&ensp;&ensp;-&ensp;타입 변환은 레지스터의 값을 다른 타입의 레지스터에 넣음으로써 가능  
&ensp;&ensp;&ensp;&ensp;-&ensp;`r1 := f2` 또는 `f3 := r4`  
&ensp;&ensp;o 라벨과 goto  
&ensp;&ensp;&ensp;&ensp;-&ensp;문장 왼쪽에 라벨:이 나타나면 해당 위치를 점프할 대상으로 기억한다  
&ensp;&ensp;&ensp;&ensp;-&ensp;`goto` 뒤에 라벨이 나타나면 해당 위치로 점프하게 된다  
&ensp;&ensp;&ensp;&ensp;-&ensp;`goto` 앞에 if 조건식이 나올 수 있음 : 조건식이 만족할 때 점프하게 된다  
&ensp;&ensp;o 입출력  
&ensp;&ensp;&ensp;&ensp;-&ensp;`input 레지스터` : 레지스터에 값을 입력 (정수 또는 실수)  
&ensp;&ensp;&ensp;&ensp;-&ensp;`print 레지스터` : 레지스터의 값을 출력  
&ensp;&ensp;&ensp;&ensp;-&ensp;`print 값` : 값을 출력  
&ensp;&ensp;&ensp;&ensp;-&ensp;`print “문자열”` : 문자열 출력 (줄바꿈 없음)  
&ensp;&ensp;o 주소의 계산  
&ensp;&ensp;&ensp;&ensp;-&ensp;배열은 인덱스는 지원되지 않고 주소 접근으로 지원  
&ensp;&ensp;&ensp;&ensp;-&ensp;레지스터에 배열주소 지정 : `r1 := &id`  
&ensp;&ensp;&ensp;&ensp;-&ensp;배열 주소 접근은 `*r1`으로  
&ensp;&ensp;o 주소를 이용한 로드 & 스토어  
&ensp;&ensp;&ensp;&ensp;-&ensp;레지스터의 주소 값으로 접근 : 그 주소에 우변의 값을 저장  
&ensp;&ensp;&ensp;&ensp;-&ensp;메모리 타입과 우변식 타입이 다르면 타입 오류 발생  
&ensp;&ensp;&ensp;&ensp;-&ensp;`*r1 := r2` 또는 `*r1 := f2`, `*r1 := 3.0`, `r2 := *r1`  
&ensp;&ensp;&ensp;&ensp;-&ensp;배열 원소의 주소는 배열주소 + 오프셋(정수 레지스터)로 표시  

![ex1]({{ site.url }}/assets/ex1.png)  


### (2) 시뮬레이터 가상기계 소개  

- 가상기계의 실행  
&ensp;&ensp;o 레지스터 : 정수 레지스터 8개와 실수레지스터 4개  
&ensp;&ensp;o 메모리 : 최대 32개의 유닛을 가짐. 정수와 실수형을 둘다 표현 가능  
&ensp;&ensp;o 타입 : 가상기계에서 다룰 수 있는 타입은 정수와 실수형임. 모든 연산은 동일한 타입에 대해서만 가능 (예외는 타입 변환 지정문)  
&ensp;&ensp;o pc 주소 : 다음 수행할 문장의 위치를 가지는 레지스터. 편집창에서 노란 줄로 표시됨  

![ex2]({{ site.url }}/assets/ex2.png)

### (3) 문장의 종류

![ex3]({{ site.url }}/assets/ex3.png)



## LPA 프로그램 가상 기계와 명령문

### (1) 레지스터
- 레지스터는 정수 레지스터(옅은청색)와 실수 레지스터(옅은녹색)로 구성됨
- r1 ~ r8과 f1~f4로 접근 가능
- 이외에 pc 레지스터가 있음
- 최종 실행에 의해 수정된 값은 빨간색으로 표시됨  
![ex4]({{ site.url }}/assets/ex4.png)

### (2) 연산 기능
1. 지정연산
  * int 지정 : r1 := r2 또는 r1 := 3 또는 r1 := int 연산식
  * float 지정 : f1 := f2 또는 f1 := 4.0 또는 f1 := float 연산식
2. 사칙연산
  * int 연산식 : r1 + r2 또는 r1 + 2 (+, -, *, /, %)
  * float 연산식 : f1 + f2 또는 f1 * 2.0 (+, -, *, /)
  * 동일한 타입(int, float)끼리만 연산 가능하고 결과도 동일한 타입이 됨
3. 비교연산
  * int 비교식 : r1 < r2 또는 r1 < 2 (==, !=, <. <=. >, >=)
  * float 비교식 : f1 < f2 또는 f1 < 2.0 (==, !=, <. <=. >, >=)
  * 동일한 타입(int, float)끼리만 비교 가능, 결과는 참 또는 거짓
4. 타입변환연산
  * 같은 타입만 연산이 가능하므로 int와 float 간 변환이 필요하게 된다. 레지스터의 값을 다른 타입으로 변환할 수 있다.
  * int에서 float로 변환 : f1 := r1
  * float에서 int로 변환 : r1 := f1
