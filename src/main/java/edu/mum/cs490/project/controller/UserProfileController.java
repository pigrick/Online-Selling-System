package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.user.*;
import edu.mum.cs490.project.service.UserService;
import edu.mum.cs490.project.utils.AESConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Erdenebayar on 4/24/2018
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value ="profile")
public class UserProfileController {

    private final UserService userService;
    private final AESConverter aesConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileController(UserService userService, PasswordEncoder passwordEncoder, AESConverter aesConverter) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.aesConverter = aesConverter;
    }

    @RequestMapping
    public String getProfile() {
        return "profile/index";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String profileEdit(@AuthenticationPrincipal Customer user, ModelMap model) {
        model.put("editForm", new CustomerForm(user));
       return "profile/editCustomer";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String profileEdit(@AuthenticationPrincipal Customer user, @Valid @ModelAttribute("editForm") CustomerForm editForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editCustomer";
        }

        if (userService.existByIdNotAndUsername(user.getId(), editForm.getUsername())) {
            model.put("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "profile/editCustomer";
        }

        user.setEmail(editForm.getEmail());
        user.setUsername(editForm.getUsername());
        user.setFirstName(editForm.getFirstName());
        user.setLastName(editForm.getLastName());
        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);
        return "redirect:/";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String adminProfileEdit(@AuthenticationPrincipal Admin user, ModelMap model) {
        model.put("editForm", new AdminForm(user));
        return "profile/editCustomer";

    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String adminProfileEdit(@AuthenticationPrincipal Admin user, @Valid @ModelAttribute("editForm") AdminForm editForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editCustomer";
        }

        if (userService.existByIdNotAndUsername(user.getId(), editForm.getUsername())){
            model.put("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "profile/editVendor";
        }

        user.setEmail(editForm.getEmail());
        user.setUsername(editForm.getUsername());
        user.setFirstName(editForm.getFirstName());
        user.setLastName(editForm.getLastName());
        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);
        return "profile/editCustomer";
    }

    @RequestMapping(value = "vendor/edit", method = RequestMethod.GET)
    public String VendorProfileEdit(@AuthenticationPrincipal Vendor user, ModelMap model) {
        model.put("editForm", new VendorForm(user));
        return "profile/editVendor";
    }

    @RequestMapping(value = "vendor/edit", method = RequestMethod.POST)
    public String VendorProfileEdit(@AuthenticationPrincipal Vendor user, @Valid @ModelAttribute("editForm") VendorForm editForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editVendor";
        }

        if (userService.existByIdNotAndUsername(editForm.getId(), editForm.getUsername())){
            model.put("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "profile/editVendor";
        }

        user.setEmail(editForm.getEmail());
        user.setUsername(editForm.getUsername());
        user.setCompanyName(editForm.getCompanyName());

        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);

        model.put("editForm", new VendorForm(user));
        return "profile/editVendor";
    }

    @RequestMapping(value = "card/edit", method = RequestMethod.GET)
    public String cardEdit(@AuthenticationPrincipal User user, ModelMap model) {
        CardDetailForm cardDetailForm = new CardDetailForm();
        model.put("editCard", cardDetailForm);
        return "profile/editCard";
    }

    @RequestMapping(value = "card/edit", method = RequestMethod.POST)
    public String cardEdit(@AuthenticationPrincipal User user, @Valid @ModelAttribute("editCard") CardDetailForm editCard, BindingResult result, ModelMap model, HttpServletRequest request) {
        if (result.hasErrors() || editCard.getCardNumber().length() != 16) {
            model.addAttribute("badcard", "Invalid Card details");
            return "profile/editCard";
        }

        //check for paymentform validation error
        if (request.getParameter("month") != null && request.getParameter("year") != null) {
            if(new Date().getYear() > Integer.parseInt(request.getParameter("year"))
                    || new Date().getMonth() > Integer.parseInt(request.getParameter("month"))) {
                model.addAttribute("badcard", "Creditcard Declined!");
                return "profile/editCard";
            }
            editCard.setCardExpirationDate(request.getParameter("month") + "/" + request.getParameter("year"));
        }
        for(CardDetail detail : user.getCards()) {
            if(detail.getCardNumber().equals(editCard.getCardNumber())) {
                encToCardDetail(detail, editCard);
                break;
            }
        }
        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);
        return "redirect:/";
    }

    @RequestMapping(value = "edit/password", method = RequestMethod.GET)
    public String profileEditPassword(ModelMap model) {
        model.put("passwordForm", new PasswordForm());
        return "profile/editPassword";
    }

    @RequestMapping(value = "edit/password", method = RequestMethod.POST)
    public String profileEditPassword(@AuthenticationPrincipal User user, @Valid @ModelAttribute("passwordForm") PasswordForm form, BindingResult error, ModelMap model) {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editPassword";
        }
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userService.saveOrUpdate(user);

        model.put("message", Message.successfullySaved);

        return "redirect:/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@AuthenticationPrincipal User user, RedirectAttributes model) {

        userService.delete(user.getId());

        model.addFlashAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));

        return "redirect://";
    }

    private void encToCardDetail(CardDetail cardDetail, CardDetailForm form) {
        cardDetail.setCardType(form.getCardType());
        cardDetail.setCardHolderName(aesConverter.encrypt(form.getCardHolderName().toUpperCase()));
        cardDetail.setCardNumber(aesConverter.encrypt(form.getCardNumber().replaceAll("\\s", "")));
        cardDetail.setCardExpirationDate(aesConverter.encrypt(form.getCardExpirationDate()));
        cardDetail.setCvv(aesConverter.encrypt(form.getCvv()));
        cardDetail.setZipcode(aesConverter.encrypt(form.getZipcode()));
        cardDetail.setLast4Digit(form.getCardNumber().substring(form.getCardNumber().length() - 4));
    }

    private void decToCardDetailForm(CardDetail cardDetail, CardDetailForm form) {
        form.setCardType(aesConverter.decrypt(cardDetail.getCardType()));
        form.setCardHolderName(aesConverter.decrypt(cardDetail.getCardHolderName()).toUpperCase());
        form.setCardNumber(aesConverter.decrypt(cardDetail.getCardNumber().replaceAll("\\s", "")));
        form.setCardExpirationDate(aesConverter.decrypt(cardDetail.getCardExpirationDate()));
        form.setCvv(aesConverter.decrypt(cardDetail.getCvv()));
        form.setZipcode(aesConverter.decrypt(cardDetail.getZipcode()));
        form.setLast4Digit(form.getCardNumber().substring(form.getCardNumber().length() - 4));
    }
}
