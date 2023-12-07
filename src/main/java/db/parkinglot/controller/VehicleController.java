package db.parkinglot.controller;

import db.parkinglot.dto.VehicleRequestDto;
import db.parkinglot.entity.Vehicle;
import db.parkinglot.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/registeredList")
    @ResponseBody
    public List<Vehicle> showVehicleList() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/registerCar")
    public String getRegisterForm() {
        return "vehicle/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Vehicle registerVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = vehicleService.registerVehicle(vehicleRequestDto);
        return vehicle;
    }

}
