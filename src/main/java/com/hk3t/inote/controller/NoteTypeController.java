package com.hk3t.inote.controller;

import com.hk3t.inote.model.Note;
import com.hk3t.inote.model.NoteType;
import com.hk3t.inote.service.NoteService;
import com.hk3t.inote.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NoteTypeController {
    @Autowired
    private NoteTypeService noteTypeService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/notetypes")
    public ModelAndView listNoteTypes(Pageable pageable) {
        Page <NoteType> noteTypes = noteTypeService.findAll(new PageRequest(pageable.getPageNumber(), 5));
        ModelAndView modelAndView = new ModelAndView("notetype/list");
        modelAndView.addObject("noteTypes", noteTypes);
        return modelAndView;
    }

    @GetMapping("/create-notetype")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("notetype/create");
        modelAndView.addObject("noteType", new NoteType());
        return modelAndView;
    }

    @PostMapping("/create-notetype")
    public String saveNoteType(@Validated @ModelAttribute NoteType noteType,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("note", new NoteType());
            model.addAllAttributes(bindingResult.getModel());
            return "notetype/create";
        } else {
            noteTypeService.save(noteType);
            model.addAttribute("noteType", noteType);
            redirectAttributes.addFlashAttribute("message", "New NoteType created successfully");
            return "redirect:/notetypes";
        }
    }

    @GetMapping("/edit-notetype/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        NoteType noteType = noteTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("notetype/edit");
        modelAndView.addObject("noteType", noteType);
        return modelAndView;
    }


    @PostMapping("/edit-notetype")
    public String updateType(@Valid @ModelAttribute NoteType noteType
            , BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("noteType", new NoteType());
            model.addAllAttributes(bindingResult.getModel());
            return "notetype/edit";
        } else {
            noteTypeService.save(noteType);
            model.addAttribute("noteType", noteType);
            redirectAttributes.addFlashAttribute("message", "New Type updated successfully");
            return "redirect:/notetypes";
        }
    }

    @RequestMapping("/delete-notetype/{id}")
    public String deleteType(@PathVariable Integer id) {
        NoteType noteType = noteTypeService.findById(id);
        noteTypeService.remove(id);
        return "redirect:/notetypes";
    }

    @GetMapping("/view-notetype/{id}")
    public ModelAndView viewType(@PathVariable Integer id, Pageable pageable) {
        NoteType noteType = noteTypeService.findById(id);
        Page <Note> notes = noteService.findAllByNoteType(noteType, pageable);
        ModelAndView modelAndView = new ModelAndView("notetype/view");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }
}
