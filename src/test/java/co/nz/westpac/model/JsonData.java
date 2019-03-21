package co.nz.westpac.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonData {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

}