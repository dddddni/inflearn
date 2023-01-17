package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberForm;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    // Setter 방식
    // public으로 된 함수이기 때문에, 중간에 변경이 되어 문제가 생길 수도 있음.
    // 생성 시점에 주입하고 그 이후로는 작업이 없는 생성자를 많이 쓰고  추천.
//    private MemberService memberService;
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 필드 주입
    // 필드 주입시 값을 변경 할 수도 없고, 불편함이 많기 때문에 잘 사용하지 않음.
    //@Autowired private MemberService memberService;


    // 생성자 주입
    private final MemberService memberService;
    // 해당 Controller가 컨테이너에 등록될때 컨테이너에 갖고 있는 MemberService와 연결 해줌.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
