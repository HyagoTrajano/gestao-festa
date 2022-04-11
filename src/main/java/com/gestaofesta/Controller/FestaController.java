package com.gestaofesta.Controller;

import java.util.List;

import com.gestaofesta.Model.Festa;
import com.gestaofesta.Model.Responsavel;
import com.gestaofesta.Repository.FestaRepository;
import com.gestaofesta.Repository.ResponsavelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FestaController {

    @Autowired
    FestaRepository festaRepository;

    @Autowired
    ResponsavelRepository responsavelRepository;
    
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

    /* Listar as festas */
    @RequestMapping("/festas")
    public ModelAndView listaFestas() {
    	ModelAndView mv = new ModelAndView("listaFestas");
    	Iterable<Festa> festas = festaRepository.findAll();
    	mv.addObject("festas", festas);
    	return mv;
    }

    @RequestMapping(value = "/{id_festa}", method = RequestMethod.GET)
    public ModelAndView detalhesFesta(@PathVariable("id_festa") long id_festa){
        Festa festas = festaRepository.getById(id_festa);
        ModelAndView mv = new ModelAndView("festa/detalhesFesta");
        mv.addObject("festas", festas);
        Iterable<Responsavel> responsavel = responsavelRepository.findByFesta(festas);
		mv.addObject("responsavel", responsavel);
        return mv;
    }

    
    @RequestMapping(value="/{id_festa}", method=RequestMethod.POST)
	public String detalhesFestaPost(@PathVariable("id_festa") long id_festa, @Validated Responsavel responsavel,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id_festa}";
		}
		Festa festa = festaRepository.getById(id_festa);
		responsavel.setFesta(festa);
		responsavelRepository.save(responsavel);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{id_festa}";
	}
    
    @RequestMapping("/deletarFesta")
	public String deletarFesta(long id_festa){
    	Festa festa = festaRepository.getById(id_festa);
    	festaRepository.delete(festa);
		return "redirect:/festas";
	}

    
    @RequestMapping("/deletarResponsavel")
	public String deletarResponsavel(long id_responsavel){
		Responsavel responsavel = responsavelRepository.getById(id_responsavel);
		responsavelRepository.delete(responsavel);
		Festa festa = responsavel.getFesta();
		long codigoLongResponsavel = festa.getId_festa();
		String codigoConvidadoResponsavel = "" + codigoLongResponsavel;
		return "redirect:/" + codigoConvidadoResponsavel;
	}
}
