package data.exceptions.handler;

import data.dto.response.ResponseDto;
import data.enums.ResultCode;
import data.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlering extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResponseDto> handleBaseException(BaseException e) {
        return buildBaseResponseMessage(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return buildBaseResponseMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMANVException(MethodArgumentNotValidException e) {
        return buildBaseResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto> handleMATMEException(MethodArgumentTypeMismatchException e) {
        return buildBaseResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseDto> buildBaseResponseMessage(String message, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .status(ResultCode.EXCEPTION)
                        .message(message)
                        .build(),
                status
        );
    }
}
