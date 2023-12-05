package org.example.springbootdeveloper.animal_type.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AnimalListDTO {
    private String name;
    private Boolean sensitive;
    private String features;
    private List<String> wellMatchedRoommates;
    private List<String> incompatibleRoommates;

    @Builder
    public AnimalListDTO(String name, Boolean sensitive, String features, List<String> wellMatchedRoommates, List<String> incompatibleRoommates)
    {
        this.sensitive = sensitive;
        this.name = name;
        this.features = features;
        this.wellMatchedRoommates = wellMatchedRoommates;
        this.incompatibleRoommates = incompatibleRoommates;
    }
}
