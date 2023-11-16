package org.example.springbootdeveloper.animal_type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserHeartDTO {
    private String rhythm;
    private String smoke;
    private int noise;
    private int temperature;

    private int outgoing;
    private int clean;
    private int sleep;

    @Builder
    public UserHeartDTO(String rhythm, String smoke, int noise, int temperature, int outgoing, int clean, int sleep)
    {
        this.rhythm = rhythm;
        this.smoke = smoke;
        this.noise = noise;
        this.temperature = temperature;
        this.outgoing = outgoing;
        this.clean = clean;
        this.sleep = sleep;
    }

}
