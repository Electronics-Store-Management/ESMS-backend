package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.checkerframework.common.aliasing.qual.Unique;

@Getter
@Setter
@NoArgsConstructor
public class StaffDTO {
    private String id;
    @Pattern(regexp = "^[a-z0-9A-Z_àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ ]*$", message = "Name should not contain special characters")
    @NotNull
    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @Email(message = "Invalid email address format")
    private String email;
    @Size.List({
            @Size(min = 12, max = 12, message = "Citizen ID must be 12 characters long")
    })
    private String citizenId;
    private Role role;
    private String photoURL;

    public StaffDTO(String id, String name, String phone, String email, String citizenId, Role role) {
        this.setId(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.citizenId = citizenId;
        this.role = role;
    }
}
