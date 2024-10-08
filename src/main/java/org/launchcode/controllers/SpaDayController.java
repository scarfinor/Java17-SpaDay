package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpaDayController {

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("oily")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating");
        } else if (skinType.equals("combination")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel");
        } else if (skinType.equals("dry")) {
            return facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial");
        } else {
            return true; // Assuming "normal" or other types have no restrictions
        }
    }

    @GetMapping(value = "")
    @ResponseBody
    public String customerForm() {
        String html = "<form method='post'>" +
                "Name: <br>" +
                "<input type='text' name='name'><br>" +
                "Skin type: <br>" +
                "<select name='skintype'>" +
                "<option value='oily'>Oily</option>" +
                "<option value='combination'>Combination</option>" +
                "<option value='normal'>Normal</option>" +
                "<option value='dry'>Dry</option>" +
                "</select><br>" +
                "Select Treatments: <br>" +
                "<input type='checkbox' name='manipedi' value='manicure'> Manicure<br>" +
                "<input type='checkbox' name='manipedi' value='pedicure'> Pedicure<br>" +
                "<input type='submit' value='Submit'>" +
                "</form>";
        return html;
    }

    @PostMapping(value = "")
    public String spaMenu(@RequestParam String name,
                          @RequestParam String skintype,
                          @RequestParam List<String> manipedi,
                          Model model) {

        // List of all possible facial treatments
        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        // Find appropriate facials based on the skin type
        ArrayList<String> appropriateFacials = new ArrayList<>();
        for (String facial : facials) {
            if (checkSkinType(skintype, facial)) {
                appropriateFacials.add(facial);
            }
        }

        // Set user profile attributes
        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manipedi", manipedi);
        model.addAttribute("appropriateFacials", appropriateFacials);

        // Determine if manicure/pedicure descriptions should be shown
        model.addAttribute("showManicure", manipedi.contains("manicure"));
        model.addAttribute("showPedicure", manipedi.contains("pedicure"));

        return "menu";  // Ensure this view exists
    }
}