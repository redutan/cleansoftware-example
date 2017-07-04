# 급여관리시스템

## 사용자 스토리

1. 새 직원을 추가한다.
2. 직원을 삭제한다.
3. 타임카드를 기록한다.
4. 판매 영수증을 기록한다.
5. 조합 공제액을 기록한다.
6. 직원 정보를 변경한다.(예: 시급, 조합비 비율)
7. 당일을 위한 급여 프로그램을 실행한다.

## 새 직원 추가하기

새로운 직원은 `AddEmp` 트랜잭션을 받는 것으로 추가딘되 이 트랜잭션은 지권의 이름, 주소, 직원번호를 포함하는데, 다음과 같은 세가지 형식이 있다.

```
AddEmp <직원번호> "<이름>" "<주소>" H <시급>
AddEmp <직원번호> "<이름>" "<주소>" S <월급>
AddEmp <직원번호> "<이름>" "<주소>" C <월급> <수수료율>
```

이 직원의 레코드 필드는 적절히 값이 할당되어 생성된다.

> 대안: 트랜잭션 구조에서의 에러

* 트랜잭션 구조가 부적당하다면, 에러 메시지를 출력하고 아무 동작도 하지 않는다.

![AddEmployeeTransaction 클래스 계층 구조](https://dooray.com/plantuml/img/IqmgBYbAJ2vHICv9B2vMS4n9SSqjoCclJ4qDAKhCAmRAPERdALHpAG21RbvYRYg4bGfLv9a2uIUcf1Ib5XU3ETf0ZeOcLoqN3iDiQdHrGHPvv9SMvLHYKK58etD-RcvcSJ5GA3M53Et1YJc9HPdOjG80)

![가능한 Employee 클래스 계층 구조](https://dooray.com/plantuml/img/IqmgBYbAJ2vHICv9B2vMSCqjoCclJ4tbWZ4KR6fqTHKyyakBSYfnI3hdv-Pc5XTdvkUbfk1G4foOarYKYIGD0000)

### 본질의 확충과 지엽적인 것의 제거

* DB(지엽적인 것)는 최대한 나중에 생각하고, 도메인(본질)을 먼저 생각하자.

## 직원 삭제

`DelEmp` 트랜잭션을 받으면 직원을 삭제한다. 이 트랜잭션의 형식은 다음과 같다.

```
DelEmp <직원번호>
```

이 트랜잭션을 받으면 해당하는 지원 레코드가 삭제된다.

> 대안: 유효하지 않거나 알 수 없는 직원번호

* `<직원번호>` 필드가 맞게 구성되어 있지 않거나 유효한 직원 레코드를 가리키지 않으면, 이 트랜잭션은 에러 메시지를 출력하고 아무 동작도 하지 않는다.

## 타임카드 기록

`TimeCard` 트랜잭션을 받으면 시스템은 타임카드 레코드를 하나 생성하고, 이것을 해당하는 직원 레코드에 연결한다.

```
TimeCard <직원번호> <날짜> <시간>
```

> 대안 1: 선택된 직원이 시간제로 일하지 않는 경우

* 시스템은 적절한 에러 메시지를 출력하고 더 이상의 동작은 취하지 않는다.

> 대안 2: 트랜잭션 구조에서의 에러

* 시스템은 적절한 에러 메시지를 출력하고 더 이상의 동작은 취하지 않는다.

![HourlyEmployee와 TimeCard 사이의 관계](https://dooray.com/plantuml/img/yyWlBSgfTCqjoCclJ4rLqDArKr0oqDFJKb88oSnDTKuiIk420000)

## 판매 영수증 기록

`SalesReceipt` 트랜잭션을 받으면 시스템은 새로운 판매 영수증 레코드를 하나 생성하고, 이것을 해당하는 직원에게 연결한다.

```
SalesReceipt <직원번호> <날짜> <액수>
```

> 대안 1: 선택된 직원이 판매 수수료를 따로 받는 직원이 아닌 경우

* 시스템은 적절한 에러 메시지를 출력하고 더 이상의 동작은 취하지 않는다.

> 대안 2: 트랜잭션 구조에서의 에러

* 시스템은 적절한 에러 메시지를 출력하고 더 이상의 동작은 취하지 않는다.

![판매 수수료를 받는 직원과 판매 영수증](https://dooray.com/plantuml/img/SyxFpSqiBixCpqjDSSqjoCclJ4rLqDArKr0oqDFJKb88Jin9BGvAJKxDB2ZX0W00)

## 조합 공제액 기록

이 트랜잭션을 받으면 시스템은 공제액 레코드 하나를 생성하고, 이것을 해당하는 조합원 레코드에 연결한다.

```
ServiceCharge <직원번호> <액수>
```

> 대안: 형식을 지키지 않은 트랜잭션

* 트랜잭션이 형식을 지키지 않았거나 `<직원번호>`가 실제로 존재하는 조합원을 가리키지 않으면 이 트랜잭션은 적절한 에러 메시지와 함께 출력된다.

*조합원과 그들의 공제액 사이에는 직접적인 연관 관계가 있다. 직원과 공제액이 아니다!!*

![조합원과 공제액](https://dooray.com/plantuml/img/2ytBpC_pJSrDIYrIqDArKr0oqDFJKb88JYqgoqnETSv8B4fFvG80)

## 직원 정보 변경

이 트랜잭션을 받으면 시스템은 해당하는 직원 레코드의 정보 중 하나를 변경한다. 이 트랜잭션에는 다양한 변형이 있을 수 있다.

```
ChgEmp <직원번호> Name <이름>                             // 직원의 이름을 변경한다.
ChgEmp <직원번호> Address <주소>                          // 직원의 주소를 변경한다.
ChgEmp <직원번호> Hourly <시급>                           // 시급을 받는 것으로 변경한다.
ChgEmp <직원번호> Salaired <월급>                         // 월급을 받는 것으로 변경한다.
ChgEmp <직원번호> Commissioned <월급> <비율>               // 수수료를 받는 것으로 변경한다.
ChgEmp <직원번호> Hold                                    // 급여 담당자에게 맡겨 놓는다.
ChgEmp <직원번호> Direct <은행> <계좌>                     // 직접 임금을 받는다.
ChgEmp <직원번호> Mail <주소>                              // 우편으로 받는다.
ChgEmp <직원번호> Member <조합원번호> Dues <조합비 비율>     // 직원을 조합에 넣는다.
ChgEmp <직원번호> NoMember                                 // 직원을 조합에서 뺀다.
```

> 대안: 트랜잭션 에러

* 이 트랜잭션의 구자가 정상적이지 않거나 `<직원번호>`가 실제 직원을 가리키지 않건 `<조합원번호>`가 이미 조합원을 가리키고 있다면, 적당한 에러를 출력하고 더 이상의 동작을 취하지 않는다.

* *새 직원 추가하기 에서 만든 `Employee` 설계가 잘못된 거 같다*
* *`Employee`는 파생이 아니라 각각의 상황에 맞는 `PaymentClassification`으로 구성된다.
    * `Hourly` : 시급
    * `Salaried` : 월급 
    * `Commissioned` : 월급 + 수수료
* *또한 `Employee`는 각각의 상황에 맞는 `PaymentMethod`으로 구성된다.
    * `Hold` : 급여 담당자에게 지급 받음
    * `Direct` : 직접 은행으로 지급 받음
    * `Mail` : 우편으로 지급 받음
* `Employee`는 조합원과 합성관계
    * 조합원 아님 : 널오브젝트
    * 조합원 : with 조합비 비율



