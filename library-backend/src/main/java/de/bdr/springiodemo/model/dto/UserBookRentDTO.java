package de.bdr.springiodemo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBookRentDTO {

    private Long id;
    @NotNull
    private User user;
    @NotNull
    private Book book;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date loanTime;
    @Size(min = 0, max = 300)
    private String note;
}
