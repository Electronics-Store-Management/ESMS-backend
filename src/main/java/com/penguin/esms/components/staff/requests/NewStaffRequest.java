package com.penguin.esms.components.staff.requests;

import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NewStaffRequest {
    @NotNull
    @Pattern(regexp = "^[a-z0-9A-Z_àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ ]*$", message = "Name should not contain special characters")
    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    private String password;
    @Email(message = "Invalid email address format")
    private String email;
    @Pattern(regexp = "^(\\d{9}|\\d{12})$", message = "Citizen ID must be 9 or 12 digits")
    private String citizenId;
    private Role role;
}
