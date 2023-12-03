package org.example.springbootdeveloper.recommend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "user_details")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "sensitivity")
    private boolean sensitive; //예민하면 true, 아니면 false
    private String animal;



    @Column(name = "dorm", nullable = false)
    private int dorm; //308, 309

    @Column(name = "room", nullable = false)
    private int room; //2(인실), 4(인실)

    @Column(name = "gender")
    private char gender;

    @Column(name = "dept")
    private String dept; //학과

    @Column(name = "stu_num")
    private Integer stu_num; //학번

    @Column(name = "age")
    private Integer age; //나이

    @Column(name = "mbti")
    private String mbti;


    @Builder
    public UserDetail(String userId, int dorm, int room, char gender, String dept, Integer stu_num, Integer age, String mbti, boolean sensitive, String animal, String nickname)
    {
        this.userId = userId;
        this.dorm = dorm;
        this.room = room;
        this.gender = gender;
        this.dept = dept;
        this.stu_num = stu_num;
        this.age = age;
        this.mbti = mbti;
        this.nickname = nickname;
        this.animal = animal;
        this.sensitive = sensitive;
    }
}
