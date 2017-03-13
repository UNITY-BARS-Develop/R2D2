package com.unitybars.r2d2.controller;

import com.unitybars.r2d2.entity.Recipient;
import com.unitybars.r2d2.exception.InvalidRequestBodyException;
import com.unitybars.r2d2.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
@RestController
@RequestMapping("/api/v1/recipients")
public class RecipientController {
    @Autowired
    private RecipientService recipientService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Recipient> getAllRecipients() {
        return recipientService.getAllRecipients();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addRecipient(@RequestBody Recipient recipient) throws InvalidRequestBodyException {
        recipientService.addRecipient(recipient);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updateRecipient(@RequestBody Recipient recipient) throws InvalidRequestBodyException {
        recipientService.updateRecipient(recipient);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Recipient getRecipient(@PathVariable("id") int recipientId) {
        return recipientService.getRecipient(recipientId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteRecipient(@PathVariable("id") int recipientId) {
        recipientService.deleteRecipient(recipientId);
    }
}