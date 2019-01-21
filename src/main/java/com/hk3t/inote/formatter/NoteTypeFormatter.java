package com.hk3t.inote.formatter;

import com.hk3t.inote.model.NoteType;
import com.hk3t.inote.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NoteTypeFormatter implements Formatter <NoteType> {
    private NoteTypeService noteTypeService;

    @Autowired
    public NoteTypeFormatter(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    @Override
    public NoteType parse(String text, Locale locale) throws ParseException {
        return noteTypeService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(NoteType object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
