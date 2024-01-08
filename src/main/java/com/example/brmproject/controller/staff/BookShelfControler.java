package com.example.brmproject.controller.staff;

import com.example.brmproject.domain.dto.BookshelfCaseDTO;
import com.example.brmproject.service.BookShelfService;
import com.example.brmproject.ultilities.StaticFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("staff")
public class BookShelfControler {

    BookShelfService bookShelfService;
    @Autowired
    public BookShelfControler(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }
    @GetMapping("/bookshelf/search/{bookId}")
    public String searchByBookId(Model model,@PathVariable Integer bookId)
        {
            try {
                BookshelfCaseDTO caseDTO = bookShelfService.searchByBookId(bookId);
                model.addAttribute("case", caseDTO);

                return "adminTemplate/bookShelf/bookshelf-detail";
            }catch (Exception e)
            {
                model.addAttribute("error",e.getMessage());
                return "/error";
            }
        }

    @GetMapping("/bookshelf/search-blank-case/{num}")
    public String showBlank(Model model,@PathVariable Integer num)
    {
        List<BookshelfCaseDTO> list=bookShelfService.findBlankCase(num);
        model.addAttribute("bookShelfList",list);
        return "adminTemplate/bookShelf/showAllCase";

    }
    @GetMapping("/bookshelf/create")
    public String create (Model model){
        BookshelfCaseDTO bookshelfCase=new BookshelfCaseDTO();
        model.addAttribute("bookshelf",bookshelfCase);
        return "adminTemplate/bookShelf/create";
    }
    @PostMapping("/bookshelf/create")
    public String create (Model model, @ModelAttribute("bookshelf") @Valid BookshelfCaseDTO bookshelfCase, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookshelf", bookshelfCase);
            return "adminTemplate/bookShelf/create";
        }

        if(bookShelfService.create(bookshelfCase)) {
            redirectAttributes.addAttribute("alertMessage","Create BookShelf success!");
        } else {
            redirectAttributes.addAttribute("alertError","Opps ! Something wrong!");
        }
        return "redirect:/staff/bookshelf/show-all";
    }

    @GetMapping("/bookshelf/show-all")
    public String showAll (Model model,@ModelAttribute("alertMessage") String alertMessage,@ModelAttribute("alertError") String alertError)
    {
        StaticFunction.showAlert(model,alertMessage,alertError);

        List<BookshelfCaseDTO> list=bookShelfService.showAll();
        model.addAttribute("bookShelfList",list);
        return "adminTemplate/bookShelf/showAllCase";
    }


}
