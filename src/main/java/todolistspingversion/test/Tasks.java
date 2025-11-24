package todolistspingversion.test;

import java.time.LocalDateTime;

public record Tasks (
        Long id,
        String task,
        LocalDateTime date,
        Boolean status
){

}
//это чтобы использовать в коде
