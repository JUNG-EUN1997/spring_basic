package com.beyond.basic.controller;

import com.beyond.basic.domain.CommonResDto;
import com.beyond.basic.domain.member.*;
import com.beyond.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // 의존성주입(DI) 방법3 ⭐
@RestController // ResponseBody 전체 붙는 기능
@RequestMapping("/rest")
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/member/list")
    public ResponseEntity<CommonResDto> memberList(){
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member List is successfully return", memberService.memberList());
        return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<CommonResDto> memberDetail(@PathVariable(value = "id") Long id){

//        return memberService.memberDetail(id);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member Detail is successfully return", memberService.memberDetail(id));
        return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    }

    @PostMapping("/member/create")
    public String memberCreatePost(@RequestBody MemberReqDto dto){
        try {
            memberService.memberCreate(dto);
            return "ok";
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return "error!!";
        }
    }


//    수정은 2가지 요청방식 : PUT, PATCH 요청
//    PATCH요청은 부분수정, PUT요청 덮어쓰기
    @PatchMapping("/member/pw/update")
//    @PutMapping, PatchMapping은, 의미만 갖고있을 뿐 따로 기능을 갖고있지는 않다. 단순약속패턴이다.
    public String memberList(@RequestBody MemberUpdateDto dto){
        memberService.pwUpdate(dto);
        return "ok";
    }

    @DeleteMapping("/member/delete/{id}")
    public String memberList(@PathVariable(value = "id") Long id){
        memberService.memberDelete(id);
        return "ok";
    }






}
