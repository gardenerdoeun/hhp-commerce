package kr.hhplus.be.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException e) {
        ErrorCode code = e.getErrorCode();
        log.error("[AppException] {} - {}", code, e.getMessage());
        ErrorResponse body = new ErrorResponse(code.getStatus().value(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("[Exception] ", e);
        ErrorResponse body = new ErrorResponse(500, "서버 에러");
        return ResponseEntity.status(500).body(body);
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private int status;
        private String message;
    }
}

