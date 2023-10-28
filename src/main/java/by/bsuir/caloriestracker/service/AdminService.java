package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Admin;
import by.bsuir.caloriestracker.repository.AdminRepository;
import by.bsuir.caloriestracker.response.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin findById(long id){
        return adminRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Admin with this id not found"));
    }
    public AdminResponse findAll(){
        return new AdminResponse(adminRepository.findAll());
    }
}
