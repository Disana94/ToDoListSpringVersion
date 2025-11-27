package todolistspingversion.test;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //он обрабатывает все исключения, которые выбрасываються в контроллере
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> handleJsonParseError(
            HttpMessageNotReadableException ex
    ){
        HashMap<String,String> error = new HashMap<>();
        error.put("error", "Недопустимые поля в запросе");
        error.put("message", "Разрешен только поле 'task'");

        return ResponseEntity.badRequest().body(error);
    }

}
