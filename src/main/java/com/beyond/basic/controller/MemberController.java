package com.beyond.basic.controller;

import com.beyond.basic.domain.member.Member;
import com.beyond.basic.domain.member.MemberReqDto;
import com.beyond.basic.domain.member.MemberResDto;
import com.beyond.basic.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 컨트롤러 기능 명시
@RequiredArgsConstructor // 의존성주입(DI) 방법3 ⭐
public class MemberController {

    @GetMapping("/")
    public String home(){
        return "/member/home";
    }

//    ⭐ 의존성주입(DI) 방법1 ⭐ 생성자 주입방식 (가장 많이 사용) : 실행 시 오류가 발생
//    장점1) final을 통해 상수로 사용 가능. 장점2) 다형성 구현 가능, 장점3)순환참조 방지
//    private final MemberService memberService;
//
//    @Autowired
//    public MemberController(MemberService memberService){
//        this.memberService = memberService;
//    }



//    ⭐ 의존성주입(DI) 방법2 ⭐ 필드주입방식 (Authowired만 사용)
//    방법 1과 동일하지만, final 키워드를 붙일 수 없다.
//          : 초기화를 시켜야하기 때문이다.
//          : 재할당이 가능하다는 단점이 있다.
//    다형성 구현이 가능하다.
//    @Autowired
//    private MemberService memberService; // 왼쪽에 클래스, 오른쪽에 인터페이스를 놓자



//    ⭐ 의존성주입(DI) 방법3 ⭐ 어노테이션(RequiredArgs)을 이용하는 방식
//    @RequiredArgsConstructor : @NonNuill어노테치션, final 키워드가 붙어있는 필드를 대상으로 생성자 생성
//    2번의 단점인 순환참조가 해결된 버전
//      다형성은 한계가 있음
    private final MemberService memberService;


//    회원목록조회
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberResDto> memberList = memberService.memberList();
        model.addAttribute("memberList",memberList);
        return "/member/member-list";
    }

//    회원상세조회 : memberDetail
//    url(uri) : member/1, member/2
//    화면명 : member-detail
    @GetMapping("/member/{id}")
//    int 또는 long으로 받을 경우 스프링에서 알아서 형변환 된다.
    public String memberDetail(@PathVariable(value = "id") int id, Model model){
        model.addAttribute("id",id);
        return "/member/member-detail";
    }


//    회원가입화면 주고
//    url : member/create
    @GetMapping("/member/create")
    public String memberCreateView(){
        return "/member/member-create";
    }

//    회원가입 데이터를 받는다
//    url : member/create
//    name, email, password
    @PostMapping("/member/create")
//    @ResponseBody
    public String memberCreatePost(MemberReqDto dto, Model model){ // reqdto로 받음
//        화면 리턴이 아닌 url 재 호출
        try {
            memberService.memberCreate(dto);
            return "redirect:/member/list"; // 화면 return이 아닌 url 재호출
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/member-error";
        }
    }




}
