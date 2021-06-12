package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        personToSave.setBirthDate(LocalDate.now());

        Person savedPerson = personRepository.save(personToSave);
        return creatMessageResponse(savedPerson.getId(), "Pessoa criada com o ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO buscar(Long id) throws PersonNotFoundException {
          Person person = verifiqueSeExiste(id);

        return personMapper.toDTO(person);
    }

    public void deletar(Long id) throws PersonNotFoundException{
        Person person =verifiqueSeExiste(id);

        personRepository.delete(person);
    }

    public MessageResponseDTO atualizar(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifiqueSeExiste(id);

        Person personToSave = personMapper.toModel(personDTO);
        personToSave.setBirthDate(LocalDate.now());

        Person updatedPerson = personRepository.save(personToSave);
        return creatMessageResponse(updatedPerson.getId(), "Pessoa atualizada com o ID ");
    }

    private MessageResponseDTO creatMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private Person verifiqueSeExiste(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
