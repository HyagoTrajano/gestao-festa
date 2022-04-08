package com.gestaofesta.Controller;

import com.gestaofesta.Model.Festa;
import com.gestaofesta.Repository.FestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FestaController {

    @Autowired
    FestaRepository festaRepository;
    
    /*End point para abrir o formulario da Festa*/
    @RequestMapping(value = "/cadFesta", method=RequestMethod.GET)
    public String formFesta(){
        return "festa/formFesta";
    }

    /* End-poin para salvar a festa no banco de dados*/
    @RequestMapping(value = "/cadFesta", method = RequestMethod.POST)
    public String formFesta(@Validated Festa festa, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadFesta";
        }

        festaRepository.save(festa);
        attributes.addFlashAttribute("mensagem","cadastrado com sucesso");
        return "redirect:/cadFesta";
    }
}
