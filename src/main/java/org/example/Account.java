package org.example;

import lombok.*;

@RequiredArgsConstructor // 처음에 final 키워드로 선언한 변수는 무조건 받을거
//// @NoArgsConstructor // 아무것도 받지 않겠음
@AllArgsConstructor // 모든 속성을 받아서 생성자 생성
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode // 메모리주소가 아니라 값 자체를 각각 비교
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsContructor
public class Account {
    // 실제 DB에는 snake_case로 컬럼명이 작성, 자바는 camelCase
    // 실제 DB에는 DB만의 자료형(int, varchar), 자바는 int와 String
    private int accountId; // 계좌번호 PK
    private final String accountNo; // 실제 계좌번호
    private String accountType; // 계좌 여부(입출금/적금)

//    public Account(int accountId, String accountNo, String accountType) {
//        this.accountId = accountId;
//        this.accountNo = accountNo;
//        this.accountType = accountType;
//    }

    @ToString.Exclude  // 특정 컬럼을 toString에서 제외
    private int balance;

//    public Account(String accountNo) {
//        this.accountNo = accountNo;
//    }

//    public int getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountNo(String accountNo) {
//        this.accountNo = accountNo;
//    }

}