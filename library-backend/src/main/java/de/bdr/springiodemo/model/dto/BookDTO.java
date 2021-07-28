package de.bdr.springiodemo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    @NotNull
    private String isbn;
    @NotNull
    private String title;
    @NotNull
    private Long authorID;
    @NotNull
    // @JsonFormat(pattern = "yyyy")
    private Date year;
    @NotNull
    private String note;
}
