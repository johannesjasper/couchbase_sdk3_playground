package de.jjasper.cbsdk;

import de.jjasper.cbsdk.data.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static de.jjasper.cbsdk.Helpers.*;

@SpringBootApplication
@Log4j2
public class CbsdkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CbsdkApplication.class, args);
    }
}