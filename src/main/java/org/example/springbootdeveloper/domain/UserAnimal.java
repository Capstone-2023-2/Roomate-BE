package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "user_animals")
public class UserAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId")
    private String userId;

    private boolean sensitive; //예민하면 1, 아니면 0
    private String animal;

    @Builder
    public UserAnimal(String userId, boolean sensitive, String animal)
    {
        this.userId = userId;
        this.sensitive = sensitive;
        this.animal = animal;
    }
}
