package one.digitalinnovation.personapi.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listaPersonDTO(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO buscar(@PathVariable Long id) throws PersonNotFoundException {
        return personService.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) throws  PersonNotFoundException{
        personService.deletar(id);

    }

    @PutMapping("/{id}")
    public MessageResponseDTO atualizar(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException{
        return personService.atualizar(id, personDTO);

    }


}
