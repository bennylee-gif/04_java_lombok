//package oop3;// oop3/Practice1Before.java
///**
// * Practice1Before: JAVA 심화 문법 실습.
// *
// * record          : 계좌 데이터를 묶는다.
// * Optional        : 조회 결과가 없을 수 있음을 표현한다.
// * Custom Exception: 실패 이유를 업무 이름으로 표현한다.
// * Lambda / Stream : 목록을 짧고 읽기 좋게 처리한다.
// * try-with-resources: 연결을 자동으로 닫는다.
// */
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class Practice1Before {
//    static final List<Account> ACCOUNTS = List.of(
//            new Account("1002-345-678901", "입출금", 1_523_000L, "지급정지"),
//            new Account("1002-345-112233", "적금", 1_099_500L, "정상"),
//            new Account("1002-345-998877", "적금", 497_000L, "휴면"));
//
//    static Optional<Account> findAccount(String accountNo) {
//        try (FakeConnection conn = new FakeConnection()) {                 // (가) try (열어놓은 자원은 ) { }가 끝나면 저절로 닫히게 됩니다.
//            conn.query("SELECT * FROM account WHERE account_no = ?");
//            for (Account a : ACCOUNTS) {
//                if (a.accountNo().equals(accountNo)) {
//                    return Optional.of(a);
//                }
//            }
////            conn.close();
//            return Optional.empty();  // Optional 로 null 여부를 옵션화할 수 있는 wrapper class로 넘겨줍니다.                                                 // (나)
//         }
//        }
//
//    static long withdraw(Account a, long amount) {
//        if (a.status().equals("지급정지")) {
//            // throw 키워드로 커스텀예외를 특정 상황에 일부러 발생시킬 수 있다.
//            throw new AccountFrozenException( "지급 정지 계좌라서 인출이 불가합니다");
//        };              // (다) AccountFrozenException
//        if (a.balance() < amount)
//        {
//            throw new InsufficientBalanceException( "계좌의 출금 가능 금액보다 이체하려는 금액이 커서 불가합니다");
//        };    // (다) InsufficientBalanceException
//        return a.balance() - amount;
//    }
//
//
//    public static void main(String[] args) {
////        Optional<Account> a = findAccount("1002-345-678901");
////        // findAccount의 결과가 있으면 Account 자료형으로 저장
////        // 다른 상황 작성해서 예외를 넘겨주면
////
////        // lambda   () -> { 실행문 }
////        Account account1 = findAccount("1002-345-678901").orElseThrow(() -> new AccountNotFoundException(
////                " 계좌를 찾을 수 없습니다."));
////
////        Account account2 = findAccount("999-000-00000").orElseThrow(() -> new AccountNotFoundException(
////                " 계좌를 찾을 수 없습니다."));
////
////        System.out.println("출금 결과: " + withdraw(account1, 100_000L));
////        // .isPresent / .orElse
//////        System.out.println(findAccount("1002-345-678901").orElse(new Account("00000", "없음", 0, "불가"))); // 있는 계좌
//////        System.out.println(findAccount("9999-000-000000").orElse(new Account("00000", "없음", 0, "불가"))); // 없는 계좌
////
////        System.out.println(findAccount("1002-345-678901").isPresent()); // 있는 계좌
////        System.out.println(findAccount("9999-000-000000").isEmpty()); // 없는 계좌
////        System.out.println("없는 계좌 잔액: " + none.balance());
//
//        // stream 단방향으로 a -> b로 처리를 하는 단축해서 쓰는 반복문
//        // 순서대로 정렬해서  v
//        // a 가 들어가는 값들만 추려서 filter -> true 인 값만을 반환
//        // 대문자로 map -> 모든 객체에 같은 로직을 적용
//        List<String> fruits = new ArrayList<>(
//                List.of("apple", "zeus", "donut", "candy")
//        );
//
//        // chaining: fruits를 1.단방향으로 흘려보내면서 2.abc순으로 정렬해서 3.a가 들어가는 값만 추려서 4.모든 객체를 대문자화 5.객체를 한줄에 하나씩 출력
//        fruits.stream().filter(word -> word.contains("a")).sorted()
//                .map(word -> word.toUpperCase()).forEach(word -> System.out.println(word));
//
//
//
//    }
//}
//
//// 기록 (데이터를 빠르게 실어나르기 위한 자바의 새로운 클래스 문법
//// a.accountNo()  이렇게 메서드를 자동으로 만들어줘서 get을 수행할 수 있다.
//// 한번 넣은 값을 수정 불가
//record Account(String accountNo, String accountType, long balance, String status) { }
//
//class FakeConnection implements AutoCloseable {
//    FakeConnection() { System.out.println("  연결 열림"); }
//
//    void query(String sql)
//    { System.out.println("  쿼리 실행"); }
//
//    @Override
//    public void close()
//    { System.out.println("  연결 닫힘"); }
//}
//
//// 커스텀 예외: 실제 자바에는 존재하지 않고, 우리의 도메인에 맞는 특정 상황을 위해 작성
//// 없는 계좌
//class AccountNotFoundException extends RuntimeException {
//    AccountNotFoundException(String message) {
//        super(message);
//        // 계좌 생성 화면으로 돌림
//    }
//}
//
//// status가 지급정지
//class AccountFrozenException extends RuntimeException {
//    AccountFrozenException(String message) {
//        super(message);
//        // 지급정지를 풀기 위한 페이지로 안내
//    }
//}
//
//// 출금금액보다 계좌의 금액이 적을 때
//class InsufficientBalanceException extends RuntimeException {
//    InsufficientBalanceException(String message) {
//        super(message);
//        // 전체 계좌 페이지로 돌아가게 한다
//    }
//}