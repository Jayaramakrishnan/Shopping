package com.crackers.model;

import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.google.common.collect.Lists;

import lombok.Data;

@NodeEntity(label = "User")
@Data
public class User implements java.io.Serializable {

	private static final long		serialVersionUID	= 1L;
	@GraphId
	private Long					id;
	private String					name;
	private String					userName;
	private String					bioData;
	private Short					isDeleted;
	private Long					idImageColorCode;
	private Long					createdBy;
	private Long					createdOn;
	private Long					updatedBy;
	private Long					updatedOn;
	@Relationship(type = "HAS_A_PASSWORD", direction = Relationship.OUTGOING)
	private UserCredential			password;
	@Relationship(type = "HAS_MULTIPLE_EMAILS", direction = Relationship.OUTGOING)
	private List<Email>				emails				= Lists.newArrayList();
	@Relationship(type = "HAS_MULTIPLE_PHONE_NUMBERS", direction = Relationship.OUTGOING)
	private List<PhoneNumber>		phoneNumbers		= Lists.newArrayList();
	@Relationship(type = "HAS_MULTIPLE_ADDRESSES", direction = Relationship.OUTGOING)
	private List<ContactDetails>	addresses			= Lists.newArrayList();
	@Relationship(type = "HAS_AN_IMAGE", direction = Relationship.OUTGOING)
	private Image					image;
	@Relationship(type = "HAS_A_ROLE", direction = Relationship.OUTGOING)
	private Role					role;
	@Relationship(type = "HAS_PREVIOUS_PASSWORDS", direction = Relationship.OUTGOING)
	private List<UserCredentialAud>	previousPasswords	= Lists.newArrayList();
	@Relationship(type = "HAS_LOGIN_TRACKS", direction = Relationship.OUTGOING)
	private List<LoginTrack>		loginTrack			= Lists.newArrayList();

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers.addAll(phoneNumbers);
	}

	public void setPhoneNumber(PhoneNumber phoneNumbers) {
		this.phoneNumbers.add(phoneNumbers);
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails.addAll(emails);
	}

	public void setEmail(Email emails) {
		this.emails.add(emails);
	}

	public List<ContactDetails> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<ContactDetails> addresses) {
		this.addresses.addAll(addresses);
	}

	public void setAddress(ContactDetails addresses) {
		this.addresses.add(addresses);
	}

	public List<UserCredentialAud> getUserCredentialAud() {
		return previousPasswords;
	}

	public void setUserCredentialAuds(List<UserCredentialAud> userCredentialAud) {
		this.previousPasswords.addAll(userCredentialAud);
	}

	public void setUserCredentialAud(UserCredentialAud userCredentialAud) {
		this.previousPasswords.add(userCredentialAud);
	}

	public List<LoginTrack> getLoginTrack() {
		return loginTrack;
	}

	public void setLoginTracks(List<LoginTrack> loginTrack) {
		this.loginTrack.addAll(loginTrack);
	}

	public void setLoginTrack(LoginTrack loginTrack) {
		this.loginTrack.add(loginTrack);
	}
}