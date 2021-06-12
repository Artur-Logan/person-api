package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtills;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

   @Mock
   private PersonRepository personRepository;

   @InjectMocks
   private PersonService personService;

   @Test
   void thenGivenpersonDTOThenReturnSaveMessage(){
       PersonDTO personDTO = PersonUtills.createFakeDTO();
       Person expectedSaveperson = PersonUtills.createFakeEntity();

       Mockito.when(personRepository.save(expectedSaveperson)).thenReturn(expectedSaveperson);

       MessageResponseDTO mensagemSucessoEsperada = MessageResponseDTO
               .builder()
               .message("Criada a pessoa com o id " + expectedSaveperson.getId())
               .build();

       Assertions.assertEquals(expectedSaveperson, mensagemSucessoEsperada);

       MessageResponseDTO mensagemDeSucesso = personService.createPerson(personDTO);

   }
}
