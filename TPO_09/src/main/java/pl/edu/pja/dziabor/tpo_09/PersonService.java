package pl.edu.pja.dziabor.tpo_09;

import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo_09.models.PersonDtoBmi;
import pl.edu.pja.dziabor.tpo_09.models.PersonDtoBmr;

import java.util.Optional;

@Service
public class PersonService {
    public PersonService() {
    }

    public Optional<PersonDtoBmi> calculateBMI(double weight, double height) {
        return  Optional.of(new PersonDtoBmi(weight, height));
    }

    public Optional<PersonDtoBmr> calculateBMR(String gender, double weight, double height, int age) {
        return  Optional.of(new PersonDtoBmr(gender, weight, height, age));
    }
}
