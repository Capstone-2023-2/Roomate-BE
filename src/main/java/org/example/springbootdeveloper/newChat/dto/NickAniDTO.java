package org.example.springbootdeveloper.newChat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NickAniDTO {
    private String animal;
    private String nickname;
    @Builder
    public NickAniDTO(String animal, String nickname)
    {
        this.animal = animal;
        this.nickname = nickname;
    }
}
