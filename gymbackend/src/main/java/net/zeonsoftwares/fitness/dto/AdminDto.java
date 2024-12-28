package net.zeonsoftwares.fitness.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDto {
    private String admin_id;
    private String admin_name;
    private String admin_email;
    private String admin_password;
    private String admin_phone;
}
