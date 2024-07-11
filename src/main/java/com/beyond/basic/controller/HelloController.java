package com.beyond.basic.controller;

import com.beyond.basic.domain.FormValue;
import com.beyond.basic.domain.Hello;
import com.beyond.basic.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
// 해당 클래스가 얜 컨트롤러야! 라고 어노테이션 명시해주고, 기능이 주입되는 것
// 컨트롤러는 사용자의 요청을 처리하고 응답하는 편의기능

//@RestController // Controller + 각 메서드마다 @ResponseBody가 붙어있는 것

// @Controller 와 @RestController는 둘 중 하나만 썼지,
//      RestController를 쓰고 하나의 메소드만 제외하는 방향으로는 작성하지 않는다.
//      즉, SSR로 하면 쭉 그걸로 하고 CSR로 하면 쭉 그걸로 한다!


@RequestMapping("/hello") // 클래스차원에 url매핑시에 RequestMapping 사용 > 앞에 무조건 /hello는 붙이고 들어가는 것
// method 차원에서도 RequestMapping 사용 가능
public class HelloController {

//    ⭐ case 1 ⭐ 사용자가 서버에게 화면요청 : get
//    ⭐ case 2 ⭐ 사용자가 서버에게 데이터 요청 : get
    @GetMapping("/")
/*
    GetMapping을 통해 get요청을 처리하고 url패턴을 명시
             추후 CSR 방식인 Vue로 개발 시, GetMapping 사용 X

    @ResponseBody
    ResponseBody 사용 시, 화면이 아닌 데이터를 return
    미사용 이고 리턴이 String일 시,
      스프링은 templates 폴더 밑에 리턴하는 값인 helloworld.html 화면을 찾아 리턴한다.
      .html은 알아서 붙여준다.
    따라서 화면을 return 하고 싶다면, ResponseBody를 빼주고, 데이터를 return하고 싶다면 붙여줘야한다.
*/
    public String helloWorld(){
        return "helloworld"; // 200 code는 똑같이 간다
    }
    /* helloWorld 코드의 의미
     * 1. BasicApplication 실행 후
     * 2. http://localhost:8080/hello 로 접속하면
     * 3. document에 [hello world] 문구 출력
     * 3-2. 또는 templates 폴더 밑에 리턴하는 값인 helloworld.html 화면을 찾아 리턴
     * */


//    ⭐ case 3 ⭐ 사용자가 json 데이터 요청(get)
//    data를 리턴하되, json형식으로 return
//    method명은 helloJson, url패턴은 /hello/json

    @GetMapping("/json")
    @ResponseBody
    public String helloJson() throws JsonProcessingException {
        Hello hello = new Hello();
        hello.setName("홍길동");
        hello.setEmail("hong@mail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(hello);
        return value; // 원래 try catch 해줘야함
    }

    @GetMapping("/json2")
    @RequestMapping(value = "/json2", method = RequestMethod.GET) // 메소드에서도 사용 가능
    @ResponseBody // ResponseBody사용하면서 객체를 return 시 자동으로 직렬화
    public Hello helloJson2() {
        Hello hello2 = new Hello();
        hello2.setName("홍길동");
        hello2.setEmail("hong@mail.com");
        return hello2;
    }

//    ⭐ case 4 ⭐ : 사용자가 json 데이터를 요청하되, parameter 형식으로 특정 객체 요청
//    get 요청 중 특정 데이터 요청  >> id 1번인 data
    @GetMapping("/param1")
    @ResponseBody
//    parameter 형식 : ?name=kim&email=mail@naver.com
    public Hello param1(@RequestParam(value="name") String inputName, @RequestParam(value="email") String inputEmail){
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail(inputEmail);
        System.out.println(hello.toString());
        return hello;
    }

//    url 패턴 : param2, 메서드 명 : Param2
//    parameter : 2개 / name, email => hello 객체 생성 후 return
//    요청방식은 & 단위
    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value="name") String inputName, @RequestParam(value="email") String inputEmail){
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail(inputEmail);
        return hello;
    }

//    ⭐ case 5 ⭐ : parameter 형식으로 요청하되, 서버에서 데이터바인딩을 하는 형식
    @GetMapping("/param3")
    @ResponseBody
//     너무 파라미터가 길어질 때 객체로 대체 가능.
//    객체에 각 변수에 맞게 알아서 바인딩(데이터 바인딩)
//    데이터 바인딩의 조건 : 기본 생성자 와 setter가 존재해야 한다
    public Hello param3(Hello hello) { // ?name=kim&email=kim@gmail.com&password=1234a
        return hello;
    }

//    ⭐ case 6 ⭐ : 서버에서 화면에 데이터를 넣어 사용자에게 return (model 객체 사용) :: SSR(server side rendering)
//                    화면 return이기 때문에 데이터를 리턴하는게 기본값인 RestController는 사용하면 안된다
    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value="name") String inputName, Model model){
//        model 객체에 name이라는 키값에 value를 세팅하면 해당 key:value는 화면으로 전달
        model.addAttribute("name",inputName);
        return "helloworld";
    }


//    ⭐ case 7 ⭐ : pathvariable(패스배리어블)방식을 통해 사용자로부터 값을 받아 화면 리턴
//                    hello/model-path/1   :  1번 사용자 값 줘! 하는 방식으로 진행
//                    url을 통해 자원의 구조를 명확하게 표현함으로, 좀 더 restful한 형식
//                          ex) author/1 : 계층구조를 좀 더 명확하게 알 수 있게끔 설계되어 있다.
    @GetMapping("/model-path/{name}")
    public String modelPath(@PathVariable String name,Model model){
        model.addAttribute("name",name);
        return "helloworld";
    }


//    =========================================================================================================
//    form view용 메서드
    @GetMapping("/form-view")
    public String formView(){ // name, email, password 입력받는 메서드
        return "formview";
    }

//    post요청(사용자입장에서 서버에 데이터를 주는 상황)
//    ⭐ case 1 ⭐ : url 인코딩방식 (text만) 전송
//    데이터 형식 : key1=value1&key2=value2...  >> parameter방식과 동일
    @PostMapping("/form-post1") // GetMapping과 같은 url패턴도 사용 가능
    @ResponseBody
    public String formPost1(@RequestParam(value="name") String inputName, @RequestParam(value="email") String inputEmail,
                            @RequestParam(value="password") String inputPassword){
//        받아온 내용 출력
        System.out.println(inputName+ " " + inputEmail + " " + inputPassword);
        return "ok";
    }


//    ⭐ case 2 ⭐ : multipart/form-data 방식(text와 파일) 전송
    /*
    * url 명 : form-file-post, 메서드명 : formFilePost, 화면명 : form-file-view
    * form태그 name, email, password, file
    */

//    form view용 메서드
    @GetMapping("/form-file-view")
    public String formFileView(){
        return "form-file-view";
    }

    @PostMapping("/form-file-post") //GetMapping과 같은 url패턴도 사용 가능
    @ResponseBody
    public String formFilePost(@ModelAttribute FormValue formValue, // @ModelAttribute는 생략 가능. 단, 데이터바인딩을 했다라는 명시적인 표시
                               @RequestParam(value="photo")MultipartFile photo){ // MultipartFile는 파일을 다운로드 받는다는 의미
        System.out.println(formValue);
        System.out.println(photo.getOriginalFilename()); // file의 오리지널 파일네임
        return "ok";
    }



//    ⭐ case 3 ⭐ : js를 활용한 [form] 데이터 전송 (text만)
    @GetMapping("/axios-form-view")
    public String axiosFormView(){
//        name, email, password 전송 : js활용
        return "axios-form-view";
    }

    @PostMapping("/axios-form-view")
    @ResponseBody
//    axios를 통해 넘어오는 형식이 key1=value&key2=value 등 >> url인코딩방식
    public String axiosFormPost(Hello hello){
        System.out.println(hello);
        return "ok";
    }


//    ⭐ case 4 ⭐ : js를 활용한 [form] 데이터 전송 (+ file)
    @GetMapping("/axios-form-file-view")
    public String axiosFormFileView(){
        return "axios-form-file-view";
    }

    @PostMapping("/axios-form-file-view")
    @ResponseBody
    public String axiosFormFilePost(Hello hello, @RequestParam(value="file")MultipartFile file){
        System.out.println(hello);
        System.out.println(file.getOriginalFilename());
        return "ok";
    }



//    ⭐ case 5 ⭐ : js를 활용한 [json] 데이터 전송
/*
url 패턴 : axios-json-view, 화면명 : axios-json-view'
get요청은 동일, post는 axiosJsonPost
이름, 이메일, password를 서버에게 던지는 것 까지만!
* */
    @GetMapping("/axios-json-view")
    public String axiosJsonView(){
        return "axios-json-view";
    }

    @PostMapping("/axios-json-view")
    @ResponseBody
//    json으로 파싱해서 객체를 만들 것 이다. 전송한 데이터를 받을 때에는 @RequestBody라는 어노테이션 사용
//          > 파라미터가 아닌, 객체로 받았다 라는 의미
//              <-> (반대의미) @ModelAttribute : 파라미터 형식으로 파싱해서 객체를 만든다.
    public String axiosJsonPost(@RequestBody Hello hello){
        System.out.println(hello);
        return "ok";
    }


//    ⭐ case 6 ⭐ : js를 활용한 [json] 데이터 전송 (+ file)
    @GetMapping("/axios-json-file-view")
    public String axiosJsonFileView(){
        return "axios-json-file-view";
    }

    @PostMapping("/axios-json-file-view")
    @ResponseBody
//    @RequestPart는, 파일과 Json을 처리할 때 주로 사용하는 어노테이션
    public String axiosJsonFilePost(
//                                  🍀 RequestParam을 사용하여 레거시한 버전으로 파일을 처리
//                                    @RequestParam("hello") String hello,
//                                    @RequestParam("file") MultipartFile file )

//        String으로 받은 뒤 수동으로 객체로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = objectMapper.readValue(hello, Hello.class);
//        System.out.println(h1.getName());

//                                  🍀 formData를 통해, json 과 file(멀티미디어)을 처리할 때 RequestPart 어노테이션을 많이 사용
//                                      영상, 이미지 등을 파트로 나눠서 처리하기 때문
                                    @RequestPart("hello") Hello hello,
                                    @RequestPart("file") MultipartFile file )
                                    throws JsonProcessingException {

        System.out.println(hello);
        System.out.println(file.getOriginalFilename());
        return "ok";
    }




//    ⭐ case 7 ⭐ : js를 활용한 [json] 데이터 전송 (+ 여러 멀티 file)
    @GetMapping("/axios-json-multi-file-view")
    public String axiosJsonMultiFileView(){
        return "axios-json-multi-file-view";
    }

    @PostMapping("/axios-json-multi-file-view")
    @ResponseBody
    public String axiosJsonMultiFilePost( @RequestPart("hello") Hello hello,
                                          @RequestPart("files") List<MultipartFile> files ){

        for (MultipartFile file : files){
            System.out.println(file.getOriginalFilename());
        }
        return "ok";
    }





//    🍀 주문서비스 Controller ==================================================
//    ⭐ case 8 ⭐ : 중첩된 JSON 데이터 처리
//    name, email, score :{math:80,music:70}
    @GetMapping("/axios-nested-json-view")
    public String axiosNestedJsonView(){
        return "axios-nested-json-view";
    }

    @PostMapping("/axios-nested-json-view")
    @ResponseBody
    public String axiosNestedJsonPost(@RequestBody Student student){ // 멍청짓햇다.. @RequestBody인데..
        System.out.println(student);
        return "ok";
    }






}
