package mk.ukim.finki.wp.lab;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LabException extends Exception {
    public LabException(String msg){
        super(msg);
    }
}
