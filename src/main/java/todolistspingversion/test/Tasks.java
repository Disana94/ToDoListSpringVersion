package todolistspingversion.test;

import java.time.LocalDateTime;

public record Tasks (
        Long id,
        String task,
        LocalDateTime date,
        Status status
){}
//это чтобы использовать в коде
