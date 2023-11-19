package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 구현체 변경을 잘 한 것 같지만...?
    // 구현 클래스에 의존을 하고 있기 때문에 나쁜 방식!
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 그래서 인터페이스에만 의존하도록 바꿔야 한다.
    // 단, 이렇게만 사용하면 당연히 객체 할당이 안 되어있으므로 NullPointException이 발생한다.
    // -> 누가 대신 '주입'해주어야 한다! (= AppConfig의 필요성)
    private final DiscountPolicy discountPolicy;

    // DIP를 지키는 코드로 변경
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 내부가 어떻게 돌아가는지 몰라도 필요한 결과를 가져올 수 있음!
        // 할인 정책이 바뀌면 이거만 바꾸면 됨(단일책임의 원칙이 잘 지켜짐)
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트용 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
