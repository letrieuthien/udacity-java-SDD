package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService users;
    private final NoteService notes;
    private final FileService files;
    private final CredentialService credentials;

    public HomeController(
            UserService users,
            NoteService notes,
            FileService files,
            CredentialService credentials
    ) {
        this.users = users;
        this.notes = notes;
        this.files = files;
        this.credentials = credentials;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        try {
            var UID = users.getUser(
                            authentication.getName()
                    ).getUserId()
                    .toString();
            model.addAttribute("UID",UID);
            model.addAttribute("notes", notes.allBy(UID));
            model.addAttribute("files", files.allBy(UID));
            model.addAttribute("credentials", credentials.allBy(UID));
        } catch (Exception ignored) {
            return "redirect:/logout-success";
        }

        return "home";
    }

}
