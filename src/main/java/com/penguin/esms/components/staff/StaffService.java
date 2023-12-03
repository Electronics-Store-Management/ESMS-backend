package com.penguin.esms.components.staff;

import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final DTOtoEntityMapper mapper;

    public void getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    }

    public StaffEntity getOne(String id) {
        Optional<StaffEntity> staff = staffRepository.findById(id);
        if (staff.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }
        return staff.get();
    }

    public List<StaffEntity> findByName(String name) {
        return staffRepository.findByNameContainingIgnoreCase(name);
    }

    public StaffEntity update(StaffDTO staffDTO, String id) {
        if (staffRepository.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Staff not existed");
        StaffEntity staff = staffRepository.findById(id).get();
        mapper.updateStaffFromDto(staffDTO, staff);
        return staffRepository.save(staff);
    }

    public void remove(String id) {
        if (staffRepository.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Staff not existed");
        staffRepository.deleteById(id);
    }
}
