package com.example.brmproject.ultilities;

import com.example.brmproject.service.BookDetailService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionDestroyListener implements HttpSessionListener {

//    @Autowired
//    private BookDetailService bookDetailService;

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // Lấy thông tin từ session (ví dụ: danh sách bookId đã đặt)
         HttpSession session = event.getSession();
        // List<Integer> bookedBookIds = (List<Integer>) session.getAttribute("bookedBookIds");

        // Duyệt qua danh sách bookId đã đặt và cập nhật lại status cho mỗi bookDetail tương ứng
        // for (Integer bookId : bookedBookIds) {
        //     bookDetailService.updateStatusToAvailable(bookId);
        // }
    }

}
