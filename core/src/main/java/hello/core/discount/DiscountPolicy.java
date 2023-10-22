package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    // F2 키를 누르면 오류가 난 곳으로 바로 이동함

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
