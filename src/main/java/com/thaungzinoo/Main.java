package com.thaungzinoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/Persons")
public class Main {

    private final PersonRepository personRepository;

    public Main(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
    @GetMapping
    public List<com.thaungzinoo.Person> getPersons(){
        return personRepository.findAll();
    }

    record NewPersonRequest(
       String name,
       String email,
       Integer age
    ){ }
    @PostMapping
    public void addPerson(@RequestBody NewPersonRequest request){
      Person person = new Person();
      person.setName(request.name());
      person.setEmail(request.email());
      person.setAge(request.age());
      personRepository.save(person);


    }
    @DeleteMapping("{personId}")
    public void deletePerson(@PathVariable("personId") Integer id){
        personRepository.deleteById(id);
    }










//    @GetMapping("/greet")
//    public GreetResponse greet(){
//
//        GreetResponse response = new GreetResponse(
//                "Hello",
//                List.of("java","python","js"),
//               new Person("kohtet",13,40000)
//
//
//        );
//      return response;
//    }
//    record Person(String name,int age,double savings){
//
//    }
//    record GreetResponse(
//            String greet,
//            List<String> favprogramminglanguages,
//            Person person
//    ){ }

//    class GreetResponse{
//
//        private final String greet;
//
//
//
//        GreetResponse(String greet) {
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
