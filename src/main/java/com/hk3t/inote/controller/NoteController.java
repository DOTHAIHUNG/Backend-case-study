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
import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Page <NoteType> noteTypes(Pageable pageable) {
        return noteTypeService.findAll(pageable);
    }

    @GetMapping("/notes")
    public ModelAndView listNote(@RequestParam(name = "search") Optional <String> search,
                                 Pageable pageable) {
        Page <Note> notes;
        if (search.isPresent()) {
            notes = noteService.findAllByTitleContaining(search.get(), new PageRequest(pageable.getPageNumber(), 5));
        } else {
            notes = noteService.findAll(new PageRequest(pageable.getPageNumber(), 5));
        }
        ModelAndView modelAndView = new ModelAndView("note/list");
        for (Note note : notes
        ) {
            if (note.getNoteType() == null) {
                note.setNoteType(new NoteType("default", "default"));
            }
        }
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/create-inotes")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create-inotes")
    public String saveCustomer(@Validated @ModelAttribute("note") Note note
            , BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("note", new Note());
            model.addAllAttributes(bindingResult.getModel());
            return "note/create";
        } else {
            noteService.save(note);
            model.addAttribute("notes", note);
            redirectAttributes.addFlashAttribute("message", "New INote created successfully");
            return "redirect:/";
        }
    }

    @GetMapping("/edit-inotes/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("note/edit");
        if (note.getNoteType() == null) {
            note.setNoteType(new NoteType("default", "default"));
        }
        modelAndView.addObject("note", note);
        return modelAndView;
    }


    @PostMapping("/edit-inotes")
    public String updateINote(@Valid @ModelAttribute Note note
            , BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("note", new Note());
            model.addAllAttributes(bindingResult.getModel());
            return "note/edit";
        } else {
            noteService.save(note);
            model.addAttribute("notes", note);
            redirectAttributes.addFlashAttribute("message", "New INote updated successfully");
            return "redirect:/notes";
        }
    }

    @GetMapping("/view-inotes/{id}")
    public ModelAndView viewCustomer(@PathVariable Integer id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("note/view");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @RequestMapping("/delete-inotes/{id}")
    public String showDeleteForm(@PathVariable Integer id) {
        Note note = noteService.findById(id);
        if (note != null) {
            noteService.remove(id);
        }
        return "redirect:/notes";
    }
}

