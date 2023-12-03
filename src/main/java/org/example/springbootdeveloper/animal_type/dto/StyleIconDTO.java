package org.example.springbootdeveloper.animal_type.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StyleIconDTO {
    private String rhythm;
    private String smoke;
    private String home;
    private String hotCold;

    @Builder
    public StyleIconDTO(String rhythm, String smoke, String home, String hotCold)
    {
        this.rhythm = rhythm;
        this.smoke = smoke;
        this.home = home;
        this.hotCold = hotCold;

    }
}
