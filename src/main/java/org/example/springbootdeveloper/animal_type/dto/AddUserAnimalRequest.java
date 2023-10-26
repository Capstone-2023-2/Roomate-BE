package org.example.springbootdeveloper.animal_type.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserAnimalRequest {
    private String userId;
    private boolean sensitive; //예민하면 1, 아니면 0
    private String animal;

}
