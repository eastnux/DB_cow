# 농장 관리 시스템

농장, 소, 고객, 그리고 구매 정보를 관리하는 애플리케이션.

## 주요 기능

이 시스템은 다음과 같은 엔티티들을 관리:

- 농장 (Farm)
- 소 (Cow)
- 고객 (Customer)
- 구매 (Purchase)

각 엔티티에 대해 다음과 같은 작업 수행 가능:

- 모든 항목 조회
- ID로 특정 항목 조회
- 새 항목 추가
- 기존 항목 수정
- 항목 삭제

## 프로젝트 구조

프로젝트는 다음과 같은 주요 클래스들로 구성:

- `Farm`, `Cow`, `Customer`, `Purchase`: 각 엔티티를 표현하는 모델 클래스
- `FarmService`, `CowService`, `CustomerService`, `PurchaseService`: 데이터베이스 작업을 처리하는 서비스 클래스
- `FarmMain`, `CowMain`, `CustomerMain`, `PurchaseMain`: 사용자 인터페이스를 제공하는 메인 클래스

## 사용 방법

각 엔티티의 Main 클래스를 실행하여 해당 엔티티에 대한 관리 작업 수행. 예를 들어, `FarmMain`을 실행하면 농장 관리 메뉴가 표시.
![image](https://github.com/user-attachments/assets/85cd00ef-3f82-4a22-8267-00f117a3190f)

번호를 입력해 해당 작업 수행 가능.
