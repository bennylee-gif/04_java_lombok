//package oop3;
//
//// oop3/PracticeAll.java
//import java.sql.*;
//import java.util.List;
//import java.util.Optional;
//
//
///**
// * Practice1Before, Practice1After를 하나로 연결한 통합 실습.
// *
// * record          : 계좌 데이터를 묶는다.
// * Optional        : 조회 결과가 없을 수 있음을 표현한다.
// * Custom Exception: 실패 이유를 업무 이름으로 표현한다.
// * Lambda / Stream : 목록을 짧고 읽기 좋게 처리한다.
// * try-with-resources: 연결을 자동으로 닫는다.
// */
//public class PracticeAll {
//
////    static final List<Account> ACCOUNTS = List.of(
////            new Account("1002-345-678901", "입출금", 1_523_000L, "지급정지"),
////            new Account("1002-345-112233", "적금", 1_099_500L, "정상"),
////            new Account("1002-345-998877", "적금", 497_000L, "휴면"));
//
//    public static void main(String[] args) {
//        System.out.println("=== 1. Optional + try-with-resources: 계좌 조회 ===");
//        Optional<Account> found = findAccount("1002-345-112233");
//        found.ifPresent(account ->
//                System.out.println("찾은 계좌: " + account.accountNo()));  //찾은 계좌: 1002-345-112233
//
//        Optional<Account> missing = findAccount("9999-000-000000");
//        System.out.println("없는 계좌인가? " + missing.isEmpty());  // 없는 계좌인가? true
//
//        System.out.println();
//        System.out.println("=== 2. Custom Exception: 출금 실패 이유 구분 ===");
//        tryWithdraw("1002-345-678901", 100_000L);
//        tryWithdraw("1002-345-998877", 600_000L);
//        tryWithdraw("1002-345-112233", 100_000L);
//
//        System.out.println();
//        System.out.println("=== 3. Lambda + Stream: 계좌 목록 가공 ===");
//        List<String> nonNormalAccounts = ACCOUNTS.stream()
//                .filter(account -> !account.status().equals("정상"))
//                .map(Account::accountNo)
//                .toList();
//        System.out.println("정상 아님: " + nonNormalAccounts);
//
//        long savingsBalance = ACCOUNTS.stream()
//                .filter(account -> account.accountType().equals("적금"))
//                .mapToLong(Account::balance)
//                .sum();
//        System.out.println("적금 잔액 합계: " + savingsBalance + "원");
//    }
//
//    static Optional<Account> findAccount(String accountNo) {
//        try (Connection connection = DBUtil.getConnection()) {  // 새로운 FakeConncection 함수를 실행해서 connection상자에 넣기
//            System.out.println("DB 연결 성공!");
//
//
//            return ACCOUNTS.stream()  //데이터를 하나씩 조회
//                    .filter(account -> account.accountNo().equals(accountNo))  //accountNo와 일치하는 데이터만 걸러줘
//                    .findFirst();  // 그 중 첫번째 계좌를 반환
//        }catch (Exception e){
//            System.out.println("DB 연결 실패 : " + e.getMessage());
//            return P
//        }
//    }
//
//    static void tryWithdraw(String accountNo, long amount) {
//        try {
//            Account account = findAccount(accountNo)
//                    .orElseThrow(() -> new AccountNotFoundException(
//                accountNo + " 계좌를 찾을 수 없습니다."));
//        long remainingBalance = withdraw(account, amount);
//        System.out.println(accountNo + " 출금 성공, 남은 잔액: "
//                + remainingBalance + "원");
//    } catch (AccountNotFoundException
//                 | AccountFrozenException
//                 | InsufficientBalanceException e) {
//            System.out.println("출금 실패: " + e.getMessage());
//        }
//    }
//
//    static long withdraw(Account account, long amount) {
//        if (account.status().equals("지급정지")) {
//            throw new AccountFrozenException(
//                    account.accountNo() + "는 지급정지 계좌라 출금할 수 없습니다.");
//        }
//        if (account.balance() < amount) {
//            throw new InsufficientBalanceException(
//                    "잔액이 " + (amount - account.balance()) + "원 부족합니다.");
//        }
//        return account.balance() - amount;
//    }
//}
//
//record Account(
//        String accountNo,
//        String accountType,
//        long balance,
//        String status
//) {
//}
//
//class AccountNotFoundException extends RuntimeException {
//    AccountNotFoundException(String message) {
//        super(message);
//    }
//}
//
//class AccountFrozenException extends RuntimeException {
//    AccountFrozenException(String message) {
//        super(message);
//    }
//}
//
//class InsufficientBalanceException extends RuntimeException {
//    InsufficientBalanceException(String message) {
//        super(message);
//    }
//}
//
//class FakeConnection implements AutoCloseable {
//    FakeConnection() {
//        System.out.println("  연결 열림");
//    }
//
//    void query(String sql) {
//        System.out.println("  쿼리 실행: " + sql);
//    }
//
//    @Override
//    public void close() {
//        System.out.println("  연결 닫힘");
//    }
//}