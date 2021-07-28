package de.bdr.springiodemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rental"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String isbn;

    @NotNull
    @Size(min = 2, max = 200)
    private String title;

    //TODO add a foreign key to authors
    @NotNull
    private Long authorID;

    @NotNull
    @Range(min = 0, max = 2021)
    // @JsonFormat(pattern = "yyyy")
    private Date year;

    @Size(min = 0, max = 300)
    private String note;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY )

    List<UserBookRent> rental;
}
