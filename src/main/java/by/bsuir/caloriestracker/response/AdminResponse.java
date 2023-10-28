package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.Admin;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AdminResponse {
    private List<Admin> admins;
}
