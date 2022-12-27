package furssov.com.ua.personrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
    private String name;
    private String secondName;
    private int age;
}
