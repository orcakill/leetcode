package other.mail.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommitDTO {
	private Date commitDate;
	private String commitContent;
}
