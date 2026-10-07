package org.example;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {

        Account account1 = new Account(1, "123-45", "예적금", 120000);
        account1.setAccountId(1);

        System.out.println(account1.getAccountId());
        System.out.println(account1.toString());
        System.out.println(account1.hashCode()); // @EqualsAndHashcode 어노테이션 없이는 메모리주소로 빠르게 연산하기 위한 숫자

        Account account2 = new Account("123-45");
        account2.setAccountId(1);
        System.out.println(account2.toString());
        System.out.println(account2.hashCode());
        System.out.println(account1.equals(account2)); // @EqualsAndHashcode 어노테이션 없이는 false 참조자료형의 속성값 자체가 아니라 메모리주소로 값 비교
    }
}