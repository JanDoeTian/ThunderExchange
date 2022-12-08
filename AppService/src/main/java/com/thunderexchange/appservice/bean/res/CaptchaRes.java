package com.gudy.counter.bean.res;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CaptchaRes {

    private String id;

    private String imageBase64;

}
