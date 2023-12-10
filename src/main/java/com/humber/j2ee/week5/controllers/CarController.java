
package com.humber.j2ee.week5.controllers;


import com.humber.j2ee.week5.models.Car;
import com.humber.j2ee.week5.repositoris.LaunchYearRepository;
import com.humber.j2ee.week5.repositoris.MakeRepository;
import com.humber.j2ee.week5.repositoris.ModelRepository;
import jakarta.validation.Valid;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.humber.j2ee.week5.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CarController implements EnvironmentAware {

    Environment environment;

    private LaunchYearRepository launchYearRepository;
    private CarService carService;
    private MakeRepository makeRepository;
    private ModelRepository modelRepository;

    @Autowired
    public CarController(CarService carService,
                         LaunchYearRepository launchYearRepository,
                         MakeRepository makeRepository,
                         ModelRepository modelRepository){
        this.carService = carService;
        this.launchYearRepository = launchYearRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "home";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "inventory";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "0") int year,
                         @RequestParam(required = false) String make,
                         @RequestParam(required = false) String model,
                         @RequestParam(required = false) String transmission,
                         Model m) {
        System.out.println("make" + make);
        System.out.println("model" + model);
        System.out.println("year" + year);
        System.out.println(transmission);
        m.addAttribute("cars", carService.getCarByAttributes(model, make, year, transmission));
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "inventory";
    }

    @GetMapping("/delete-car")
    public String deleteCar(@RequestParam String vin, Model model) {
        carService.deleteCarById(vin);
        model.addAttribute("message", "Car deleted successfully.");
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "inventory";
    }

    @PostMapping("/add/car")
    public String addCar(Model model, @Valid @ModelAttribute Car car
            , BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("appName",environment.getProperty("application.name"));
            model.addAttribute("car",car);
            if(!car.getVin().getVinNumber().isEmpty()) model.addAttribute("edit",true);
            return "add-car";
        }
        this.carService.addCar(car);
        return "redirect:/inventory";
    }

    @GetMapping("/add/car")
    public String addCarPage(Model model){
        model.addAttribute("appName",environment.getProperty("application.name"));
        model.addAttribute("car",new Car());
        model.addAttribute("carYears", launchYearRepository.findAll());
        model.addAttribute("makes", makeRepository.findAll());
        model.addAttribute("models", modelRepository.findAll());
        return "add-car";
    }
}
 