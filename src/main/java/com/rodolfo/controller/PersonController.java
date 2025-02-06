package com.rodolfo.controller;

import com.rodolfo.business.PersonService;
import com.rodolfo.business.dto.CellphoneDTO;
import com.rodolfo.business.dto.PersonDTO;
import com.rodolfo.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {

	@Autowired
	private final PersonService personService;
	@Autowired
	private final AuthenticationManager authenticationManager = null;
	@Autowired
	private final JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<PersonDTO> personSave(@RequestBody PersonDTO personDTO) {
		return ResponseEntity.ok(personService.personSave(personDTO));
	}
	
	@PostMapping("/login")
	public String login(@RequestBody PersonDTO personDTO) {
		Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(personDTO.getEmail(), personDTO.getPassword()));
		return "Bearer " + jwtUtil.generateToken(authentication.getName());
		
	}
	
	@GetMapping
	public ResponseEntity<PersonDTO> searchPersonByEmail(@RequestParam("email") String email) {
		return ResponseEntity.ok(personService.searchPersonByEmail(email));
		
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletePersonByEmail(@PathVariable String email) {
		personService.deletePersonByEmail(email);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO, @RequestHeader("Authorization") String token){
		return ResponseEntity.ok(personService.updatePerson(token, personDTO));
	}
	
	@PutMapping("/cellphone")
	public ResponseEntity<CellphoneDTO> cellphoneUpdate(@RequestBody CellphoneDTO cellphoneDTO, @RequestParam("id") Long id){
		return ResponseEntity.ok(personService.cellphoneUpdate(id, cellphoneDTO));
	}
	
	@PostMapping("/cellphone")
	public ResponseEntity<CellphoneDTO> cellphoneRegister(@RequestBody CellphoneDTO cellphoneDTO, 
				@RequestHeader("Authorization")String token){
		return ResponseEntity.ok(personService.cellphoneRegister(token, cellphoneDTO));
	}
}





