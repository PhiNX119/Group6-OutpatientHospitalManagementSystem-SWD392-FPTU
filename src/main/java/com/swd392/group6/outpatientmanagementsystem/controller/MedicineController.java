package com.swd392.group6.outpatientmanagementsystem.controller;

import com.swd392.group6.outpatientmanagementsystem.model.dto.MedicineDto;
import com.swd392.group6.outpatientmanagementsystem.model.entity.Medicine;
import com.swd392.group6.outpatientmanagementsystem.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    /**
     * @author phinx
     * @description redirect to medicine list page
     */
    @GetMapping("/list")
    public String showMedicineList(Model model) {
        List<Medicine> medicineList = medicineService.findAll();
        model.addAttribute("medicineList", medicineList);

        return "medicine/list";
    }

    /**
     * @author phinx
     * @description show customer detail
     */
    @GetMapping("/detail")
    public String viewCustomerDetail(@RequestParam("id") Integer medicineId, Model model) {
        Medicine medicine = medicineService.getMedicineById(medicineId);
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.loadFromEntity(medicine);
        model.addAttribute("medicineDto", medicineDto);

        return "medicine/detail";
    }

    /**
     * @author phinx
     * @description redirect to add medicine page
     */
    @GetMapping("/add")
    public String addNewMedicine(Model model) {
        MedicineDto medicineDto = new MedicineDto();
        model.addAttribute("medicineDto", medicineDto);

        return "medicine/add";
    }

    /**
     * @author phinx
     * @description add new medicine
     */
    @PostMapping("/add")
    public String addNewMedicine(@Valid @ModelAttribute("medicineDto") MedicineDto medicineDto,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("medicineDto", medicineDto);
            return "medicine/add";
        } else {
            medicineDto.setActive(true);
            medicineService.addNewMedicine(medicineDto);
            return "redirect:/medicine/list?addSuccess";
        }
    }
}
