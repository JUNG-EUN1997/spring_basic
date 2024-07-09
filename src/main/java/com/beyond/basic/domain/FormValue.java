package com.beyond.basic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class FormValue {
    String name;
    String email;
    String password;
    MultipartFile photo;
}
