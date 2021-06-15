package br.com.marques.os.dtos;

import br.com.marques.os.domain.OS;
import br.com.marques.os.domain.enums.Priority;
import br.com.marques.os.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class OsDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime openingDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime closingDate;

    private Priority priority;

    @NotEmpty(message = "The COMMENTS field is required!")
    private String comments;

    private Status status;
    private Long client;
    private Long technician;

    public OsDTO(OS obj) {
        this.id = obj.getId();
        this.openingDate = obj.getOpeningDate();
        this.closingDate = obj.getClosingDate();
        this.priority = obj.getPriority();
        this.comments = obj.getComments();
        this.status = obj.getStatus();
        this.client = obj.getClient().getId();
        this.technician = obj.getTechnician().getId();
    }
}
