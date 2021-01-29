package com.lambdaschool.schools.services;


import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.hibernate.exception.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
// Bean not found issue is cause your service and value isn't the exact spelling.
@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions {
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        List<ValidationError> listVE = new ArrayList<>();

        while ((cause != null) && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException)){
//            iterates through the next cause in the chain until 1 of 2 violations are found
            cause = cause.getCause();
        }

        if (cause != null){
            if(cause instanceof ConstraintViolationException) {
//                ConstraintViolationException
                ConstraintViolationException ex = (ConstraintViolationException) cause;

                ValidationError newVE = new ValidationError();
                newVE.setFieldName(ex.getMessage());
                newVE.setMessage(ex.getConstraintName());

                listVE.add(newVE);
            } else {
//                MethodArgumentNotValid
//                Type case it so you can access its methods.
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for (FieldError fe: fieldErrors){
                    ValidationError newVE = new ValidationError();

                    newVE.setFieldName(fe.getField());
                    newVE.setMessage(fe.getDefaultMessage());

                    listVE.add(newVE);
                }
            }
        }

        return listVE;
    }
}
