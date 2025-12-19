package todolistspingversion.test;

import jakarta.validation.constraints.NotBlank;

public record TaskDto(
        @NotBlank
        String task

) {}
