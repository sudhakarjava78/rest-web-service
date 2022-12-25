package com.social.media.webservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Sudhakar Sid");//A consumer //name fn+ln
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Sudhakar","Sid"));//B consumer//fn,ln
	}
	
	@GetMapping(path="/person",params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParamater() {
		return new PersonV1("Sudhakar Sid");
	}
	
	@GetMapping(path="/person",params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Sudhakar","Sid"));
	}
	
	/*
	 * @GetMapping(path="/person/header",headers="X-API-VERSION=1") public PersonV1
	 * getFirstVersionOfPersonRequestHeader() { return new PersonV1("Sudhakar Sid");
	 * }
	 * 
	 * @GetMapping(path="/person/header",headers="X-API-VERSION=2") public PersonV2
	 * getSecondVersionOfPersonRequestHeader() { return new PersonV2(new
	 * Name("Sudhakar","Sid")); }
	 */
	
	@GetMapping(path="/person/accept",produces ="application/v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Sudhakar Sid");
	}
	
	@GetMapping(path="/person/accept",produces ="application/v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Sudhakar","Sid"));
	}
}
