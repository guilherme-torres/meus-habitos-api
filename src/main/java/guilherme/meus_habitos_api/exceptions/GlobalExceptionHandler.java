package guilherme.meus_habitos_api.exceptions;

import guilherme.meus_habitos_api.dto.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponseDto(HttpStatus.NOT_FOUND, exception.getMessage())
        );
    }

    @ExceptionHandler(HabitNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleHabitNotFoundException(HabitNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponseDto(HttpStatus.NOT_FOUND, exception.getMessage())
        );
    }
}
