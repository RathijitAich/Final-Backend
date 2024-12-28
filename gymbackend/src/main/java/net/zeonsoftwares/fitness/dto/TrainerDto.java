package net.zeonsoftwares.fitness.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerDto {
    private String trainer_id;
    private String trainer_name;
    private String trainer_email;
    private String trainer_password;
    private String trainer_phone;
    //many to one with admin in trainer table
    private String trainer_admin_id;


}
