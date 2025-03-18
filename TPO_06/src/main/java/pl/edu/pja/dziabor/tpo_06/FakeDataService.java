package pl.edu.pja.dziabor.tpo_06;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FakeDataService {
    private List<PersonDTO> people = new ArrayList<>();
    public List<PersonDTO> getList(int value, List<String> additionalFields, String language) {
        Faker faker = new Faker(new Locale(language));
        people.clear();
        for (int i = 0; i < value; i++) {
            Name name = faker.name();
            PersonDTO person = new PersonDTO(name.firstName(), name.lastName(), faker.date().birthday());

            for (String additionalField : additionalFields) {
                switch (additionalField) {
                    case "occupation":
                        person.setOccupation(faker.job().title());
                        break;
                    case "country":
                        person.setCountry(faker.country().name());
                        break;
                    case "address":
                        person.setAddress(faker.address().fullAddress());
                        break;
                    case "industry":
                        person.setIndustry(faker.company().industry());
                        break;
                    case "phoneNumber":
                        person.setPhoneNumber(faker.phoneNumber().phoneNumber());
                        break;
                    case "email":
                        person.setEmail(faker.internet().emailAddress());
                        break;
                    case "sport":
                        person.setSport(faker.team().sport());
                        break;
                    case "university":
                        person.setUniversity(faker.university().name());
                    default:
                        break;
                }
            }
            people.add(person);
        }
        return people;
    }

    public List<PersonDTO> getList(int value, String language) {
        Faker faker = new Faker(new Locale(language));
        people.clear();
        for (int i = 0; i < value; i++) {
            Name name = faker.name();
            PersonDTO person = new PersonDTO(name.firstName(), name.lastName(), faker.date().birthday());
            people.add(person);
        }
        return people;
    }
}
