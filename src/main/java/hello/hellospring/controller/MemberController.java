package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
//    private final MemberService memberService = new MemberService();
    // Spring 컨테이너에 등록하고, Spring 컨테이너에서 받아서 쓰도록 변경해야 함
    // 위와 같이 선언하면, MemberController 말고도 다른 모든 컨트롤러에서 객체가 새로 생성될 것..
    // Service를 굳이 여러 개의 인스턴스를 생성할 필요가 없음, 공용으로 써도 됨
    // Spring Container 에 등록하고 쓰자! (하기와 같이)

    private final MemberService memberService;

    @Autowired // Spring 이 뜰 때, 컨트롤러 객체가 생성되는데, Autowired 어노테이션이 이렇게 있으면, Spring 컨테이너에 있는 Service 를 연결 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
