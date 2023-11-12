package org.example.springbootdeveloper.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.recommend.domain.UserDetail;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUserDetailRequest {
    private String userId;
    private int dorm;
    private int room;
    private char gender;
    private String dept;
    private Integer stu_num;
    private Integer age;
    private String mbti;

    public UserDetail toEntity(String userId){
        return UserDetail.builder()
                .userId(userId)
                .dorm(dorm)
                .room(room)
                .gender(gender)
                .dept(dept)
                .stu_num(stu_num)
                .age(age)
                .mbti(mbti)
                .build();
    }
}
