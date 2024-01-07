package com.example.brmproject.controller.customer;

import com.example.brmproject.domain.dto.OrderDetailDTO;
import com.example.brmproject.domain.dto.ReviewRatingDTO;
import com.example.brmproject.service.imp.ReviewRatingServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class ReviewRatingController {
    private ReviewRatingServiceImp reviewRatingService;

    public ReviewRatingController(ReviewRatingServiceImp reviewRatingService) {
        this.reviewRatingService = reviewRatingService;
    }

    @GetMapping("/review/{orderId}/{orderDetailId}")
    public String reviewRating(@PathVariable("orderDetailId") int orderDetailId,
                               Model model,
                               @PathVariable("orderId") int orderId
    ) {
        model.addAttribute("orderDetailId", orderDetailId);
        model.addAttribute("reviewObj", new OrderDetailDTO());
        model.addAttribute("orderId", orderId);
        int bookId = 1;
        ReviewRatingDTO reviewRatingDTO = reviewRatingService.getReviewRatingByBook(bookId);

        System.out.println(reviewRatingDTO.getAvrRating());
        reviewRatingDTO.getListReview().forEach(review -> {
            System.out.println(review.getReview());
            System.out.println(review.getRating());
            System.out.println(review.getUsername());
        });

        return "/customerTemplate/review/reviewBook";
    }

    @PostMapping("/review/{orderId}/{orderDetailId}")
    public String reviewRating(@ModelAttribute OrderDetailDTO reviewObj,
                               @PathVariable("orderDetailId") int orderDetailId,
                               @PathVariable("orderId") int orderId
    ) {

        reviewRatingService.createReviewRating(reviewObj, orderDetailId);

        return "redirect:/customers/orders/" + orderId;
    }
}
