package org.example.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.domain.UserAnimal;
import org.example.springbootdeveloper.domain.UserStyle;

@Getter
@Setter
public class AddUserAnimalRequest {
    private String userId;
    private boolean sensitive; //예민하면 1, 아니면 0
    private String animal;

}
