package oop3;// oop3/Practice1Before.java
/**
 * Practice1Before: JAVA 심화 문법 실습.
 *
 * record          : 계좌 데이터를 묶는다.
 * Optional        : 조회 결과가 없을 수 있음을 표현한다.
 * Custom Exception: 실패 이유를 업무 이름으로 표현한다.
 * Lambda / Stream : 목록을 짧고 읽기 좋게 처리한다.
 * try-with-resources: 연결을 자동으로 닫는다.
 */
import java.util.List;
import java.util.Optional;

public class Practice1Before {
    static final List<Account> ACCOUNTS = List.of(
            new Account("1002-345-678901", "입출금", 1_523_000L, "지급정지"),
            new Account("1002-345-112233", "적금", 1_099_500L, "정상"),
            new Account("1002-345-998877", "적금", 497_000L, "휴면"));

    static Optional<Account> findAccount(String accountNo) {
        FakeConnection conn = new FakeConnection();                  // (가)
        conn.query("SELECT * FROM account WHERE account_no = ?");
        for (Account a : ACCOUNTS) {
            if (a.accountNo().equals(accountNo)) {
                return Optional.of(a);
            }
        }
        conn.close();
        return Optional.empty();  // Optional 로 null 여부를 옵션화할 수 있는 wrapper class로 넘겨줍니다.                                                 // (나)
    }

//    static boolean withdraw(Optional<Account> a, long amount) {
//        if (a.status().equals("지급정지")) return false;              // (다)
//        if (a.balance() < amount) return false;                      // (다)
//        return true;
//    }

    public static void main(String[] args) {
        Optional<Account> a = findAccount("1002-345-678901");
//        System.out.println("출금 결과: " + withdraw(a, 100_000L));
        Optional<Account> none = findAccount("9999-000-000000");
        // .isPresent / .orElse
        System.out.println(findAccount("1002-345-678901").orElse(new Account("00000", "없음", 0, "불가"))); // 있는 계좌
        System.out.println(findAccount("9999-000-000000").orElse(new Account("00000", "없음", 0, "불가"))); // 없는 계좌

        System.out.println(findAccount("1002-345-678901").isPresent()); // 있는 계좌
        System.out.println(findAccount("9999-000-000000").isEmpty()); // 없는 계좌
//        System.out.println("없는 계좌 잔액: " + none.balance());
    }
}

// 기록 (데이터를 빠르게 실어나르기 위한 자바의 새로운 클래스 문법
// a.accountNo()  이렇게 메서드를 자동으로 만들어줘서 get을 수행할 수 있다.
// 한번 넣은 값을 수정 불가
record Account(String accountNo, String accountType, long balance, String status) { }

class FakeConnection implements AutoCloseable {
    FakeConnection() { System.out.println("  연결 열림"); }

    void query(String sql)
    { System.out.println("  쿼리 실행"); }

    @Override
    public void close()
    { System.out.println("  연결 닫힘"); }
}