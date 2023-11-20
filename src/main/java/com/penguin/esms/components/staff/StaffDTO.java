package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    @Pattern(regexp = "^[a-z0-9A-Z_àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ ]*$", message = "Name should not contain special characters")
    @NotNull
    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @Size(min = 1, message = "Password must be at least 1 characters long")
    private String password;
    @Email(message = "Invalid email address format")
    private String email;
    @Size.List({
            @Size(min = 12, max = 12, message = "Citizen ID must be 12 characters long")
    })
    private String citizenId;
    private Role role;
}
