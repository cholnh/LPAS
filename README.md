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
&ensp;&ensp;&ensp;&ensp;-  r1, r2와 같은 정수 레지스터와 f1, f2와 같은 실수 레지스터가 있다  
&ensp;&ensp;&ensp;&ensp;-  정수 레지스터는 `1~8번`, `r1~r8`까지 사용할 수 있음 (다른 번호는 오류 발생)  
&ensp;&ensp;&ensp;&ensp;-  실수 레지스터는 `1~4번`, `f1~f4`까지 사용할 수 있음 (다른 번호는 오류 발생)  
&ensp;&ensp;o 변수이름  
&ensp;&ensp;&ensp;&ensp;-  이름에 해당하는 토큰 (키워드, 레지스터 제외)  
&ensp;&ensp;o 라벨  
&ensp;&ensp;&ensp;&ensp;-  L1, L2와 같이 L로 시작하고 한자리 숫자로 표현됨 (L1~L9)  
&ensp;&ensp;o 리터럴  
&ensp;&ensp;&ensp;&ensp;-  정수, 소수, 또는 쌍따옴표로 묶인 문자열  
&ensp;&ensp;&ensp;&ensp;-  값의 제약 없음  
&ensp;&ensp;o 키워드  
&ensp;&ensp;&ensp;&ensp;-  program, end (프로그램의 시작과 끝에 반드시 나와야 하는 키워드임)  
&ensp;&ensp;&ensp;&ensp;-  if, goto, input, print  
&ensp;&ensp;o 기호  
&ensp;&ensp;&ensp;&ensp;-  :=, 수치연산 기호(+, -, *, /, %), 비교연산 기호(<. <=. ==, !=, >, >=)  
&ensp;&ensp;&ensp;&ensp;-  괄호(조건식 둘러싸는 곳)  
&ensp;&ensp;&ensp;&ensp;-  : (라벨 바로 뒤에)  
&ensp;&ensp;&ensp;&ensp;-  & (주소연산), *(간접접근연산)  
  
