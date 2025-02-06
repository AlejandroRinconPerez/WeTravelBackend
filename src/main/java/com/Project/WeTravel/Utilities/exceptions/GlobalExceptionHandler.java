package com.Project.WeTravel.Utilities.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

//    // Handle NotFoundException
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
//        ErrorResponses errorResponse = new ErrorResponses(
//                "Not found",
//                ex.getMessage(),
//                HttpStatus.NOT_FOUND,
//                HttpStatus.NOT_FOUND.value()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    // Handle InvalidInputException
//    @ExceptionHandler(InvalidInputException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
//        ErrorResponses errorResponse = new ErrorResponses(
//                "Invalid input",
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST,
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//    
//    // Handle TypeMismatchException
//    @ExceptionHandler(TypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleTypeMismatchException(TypeMismatchException ex) {
//        ErrorResponses errorResponse = new ErrorResponses(
//                "Type mismatch error",
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST,
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//    
//    // Handle UniqueViolationException
//    @ExceptionHandler(UniqueViolationException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ResponseEntity<Object> handleUniqueViolationException(UniqueViolationException ex) {
//        ErrorResponses errorResponse = new ErrorResponses(
//                "Unique error",
//                ex.getMessage(),
//                HttpStatus.CONFLICT,
//                HttpStatus.CONFLICT.value()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//    }
//
//    
//    // Generic exceptions 
//    
//    // Not found url 
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<ErrorResponses> notFoundEx(NoHandlerFoundException e) {
//            ErrorResponses error = new ErrorResponses();
//            error.setError("Dirección inválida 😢...");
//            error.setMessage(e.getMessage());
//            error.setStatus(HttpStatus.NOT_FOUND); // + 404
//            error.setStatusId(HttpStatus.NOT_FOUND.value());
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // + 404
//        }
//
//
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ErrorResponses> methodNotAllowedEx(HttpRequestMethodNotSupportedException ex) {
//            ErrorResponses error = new ErrorResponses();
//
//            error.setError("Método HTTP no permitido.");
//            error.setMessage("Método '" + ex.getMethod() + "' no es soportado para esta ruta.");
//            error.setStatus(HttpStatus.METHOD_NOT_ALLOWED); 
//            error.setStatusId(HttpStatus.METHOD_NOT_ALLOWED.value()); // + 405
//
//            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
//    }
//
//    // ! CUANDO LA CONEXION A LA DB FALLE
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<ErrorResponses> databaseConnectionEx(DataAccessException ex) {
//            ErrorResponses error = new ErrorResponses();
//            error.setError("Error de conexión a la base de datos");
//            error.setMessage("No se pudo conectar a la base de datos. Verifica que el servicio esté en funcionamiento.");
//            error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); 
//            error.setStatusId(HttpStatus.INTERNAL_SERVER_ERROR.value()); // + 500
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//        
//    }
//
//    // Generic exception
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponses> handleGenericException(Exception ex) {
//        ErrorResponses error = new ErrorResponses();
//        error.setError("Error interno del servidor");
//        error.setMessage("Ocurrió un error inesperado. Por favor, intente nuevamente más tarde.");
//        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        error.setStatusId(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//}

    
}
